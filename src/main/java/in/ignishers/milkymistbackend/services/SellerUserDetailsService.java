package in.ignishers.milkymistbackend.services;

import in.ignishers.milkymistbackend.models.Seller;
import in.ignishers.milkymistbackend.repos.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerUserDetailsService implements UserDetailsService {

    private final SellerRepository sellerRepository;

    @Override
    public UserDetails loadUserByUsername(String sellerId) throws UsernameNotFoundException {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new UsernameNotFoundException("No seller found with id: " + sellerId));

        // Every authenticated request is a "seller" for now.
        // When read-only customer logins are added later, load them from a
        // separate table/service and give them ROLE_CUSTOMER here instead.
        return User.builder()
                .username(seller.getSellerId())
                .password(seller.getPasswordHash()) // already a BCrypt hash in the DB
                .roles("SELLER")
                .build();
    }
}

