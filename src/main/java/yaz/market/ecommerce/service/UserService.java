package yaz.market.ecommerce.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import yaz.market.ecommerce.Iservice.IUserService;
import yaz.market.ecommerce.dao.UserRepository;
import yaz.market.ecommerce.exception.NotFoundExecption;
import yaz.market.ecommerce.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        Optional<User> user=userRepository.findById(id);
        if (!user.isPresent()){
            throw new NotFoundExecption("no user Found");
        }
        return user.get();

    }

    @Override
    public User getCurrentUser() {
        return null;
    }
}
