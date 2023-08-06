package yaz.market.ecommerce.Iservice;

import yaz.market.ecommerce.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface IUserService {

    public List<User> getAllUsers();
    public User getUserById(long id);
   public User getCurrentUser();
}
