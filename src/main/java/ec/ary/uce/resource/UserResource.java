package ec.ary.uce.resource;
import ec.ary.uce.util.User;
import ec.ary.uce.jpa.UserService;
import ec.ary.uce.jpaUtil.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.stream.Collectors;

@Path("/users")
public class UserResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/all")
    public String getAllUsers() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            List<User> users = userService.getAllUsers();
            if (users.isEmpty()) {
                return "No users found";
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(String.format("%-10s %-20s\n", "ID", "Name"));
                sb.append("------------------------------------\n");
                for (User user : users) {
                    sb.append(String.format("%-10d %-20s\n", user.getId(), user.getName()));
                }
                return sb.toString();
            }
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/create")
    public String createUser() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.createUser(new User("user1"));
            userService.createUser(new User("user2"));
            return "Users created successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/update")
    public String updateUser() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.updateUser(new User(1, "user0"));
            return "User updated successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/delete")
    public String deleteUser() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.deleteUser(2);
            return "User deleted successfully!";
        } finally {
            em.close();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{id}")
    public String getUserById(@PathParam("id") int id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            User user = userService.getUserById(id);
            if (user != null) {
                return String.format("%-10s %-20s\n%-10d %-20s",
                        "ID", "Name",
                        user.getId(), user.getName());
            } else {
                return "User not found";
            }
        } finally {
            em.close();
        }
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String createUser(User user) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.createUser(user);
            return "User created successfully!";
        } finally {
            em.close();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String updateUser(User user) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.updateUser(user);
            return "User updated successfully!";
        } finally {
            em.close();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String deleteUser(@PathParam("id") int id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            UserService userService = new UserService(em);
            userService.deleteUser(id);
            return "User deleted successfully!";
        } finally {
            em.close();
        }
    }
}

