package com.revature.revshopserver.jwt;

import com.revature.revshopserver.entities.Account;
import com.revature.revshopserver.enums.AccountType;
import com.revature.revshopserver.repositories.AccountRepository;
import com.revature.revshopserver.repositories.BuyerRepository;
import com.revature.revshopserver.repositories.SellerRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${token.signing.key}")
    private String jwtSigningKey;

    private final AccountRepository accountRepository;
    private final BuyerRepository buyerRepository;
    private final SellerRepository sellerRepository;

    @Autowired
    public JwtServiceImpl(AccountRepository accountRepository, BuyerRepository buyerRepository, SellerRepository sellerRepository) {
        this.accountRepository = accountRepository;
        this.buyerRepository = buyerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();
        // getAuthorities() currently returns a list with just the accountType stored in it,
        // want to retrieve the string value of the accountType
        extraClaims.put("accountType", userDetails.getAuthorities());
        String accountString = ((List<Object>) extraClaims.get("accountType")).get(0).toString();
        AccountType accountType = null;
        if (accountString.equals("BUYER")) {
            accountType = AccountType.BUYER;
        } else if (accountString.equals("SELLER")) {
            accountType = AccountType.SELLER;
        }
        Integer specId = getSpecIdFromUsername(userDetails.getUsername(), accountType);
        extraClaims.put("specificId", specId);
        return generateToken(extraClaims, userDetails);
    }

    public Integer getSpecIdFromUsername(String username, AccountType accountType) {
        Integer accountId = accountRepository.findByUsername(username).get().getaccountId();
        switch (accountType) {
            case BUYER -> {
                return buyerRepository.findByAccount_AccountId(accountId).get().getBuyerId();
            }
            case SELLER -> {
                return sellerRepository.findByAccount_AccountId(accountId).get().getSellerId();
            }
        }
        return null;
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }

    private String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
