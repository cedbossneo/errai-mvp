package eu.moncompte.beans;

import eu.moncompte.model.User;
import eu.moncompte.model.UserRoles;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class UserBean  {

    @Inject
    private Logger log;

    @PersistenceContext
    private EntityManager entityManager;

    public User register(User newUser) {
        log.info("Registering " + newUser.getName());
        newUser.setPassword(encodeMD5(newUser.getPassword()));
        entityManager.persist(newUser);
        return newUser;
    }

    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u").getResultList();
    }

    public User login(String username, String password) {
        password = encodeMD5(password);
        log.info("Try to login user " + username + " with password " + password);
        User singleResult = (User) entityManager.createQuery("SELECT u FROM User u WHERE u.email = :username AND u.password = :password").setParameter("username", username).setParameter("password", password).getSingleResult();
        return singleResult;
    }

    public void addRole(User user, UserRoles.Roles role) {
        entityManager.merge(user);
        UserRoles newRole = new UserRoles();
        newRole.setRole(role);
        newRole.setUser(user);
        user.getRoles().add(newRole);
        entityManager.persist(newRole);
    }

    public void deleteRole(User user, UserRoles.Roles role) {
        entityManager.merge(user);
        user.getRoles().remove(role);
        entityManager.remove(role);
    }

    private String encodeMD5(String password) {
        byte[] uniqueKey = password.getBytes();
        byte[] hash = null;

        try {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        } catch (NoSuchAlgorithmException e) {
            throw new Error("No MD5 support in this VM.");
        }

        StringBuilder hashString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            } else
                hashString.append(hex.substring(hex.length() - 2));
        }
        return hashString.toString();
    }
}
