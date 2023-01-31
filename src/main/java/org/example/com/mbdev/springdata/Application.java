package org.example.com.mbdev.springdata;

import org.example.com.mbdev.springdata.entity.Account;
import org.example.com.mbdev.springdata.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public void run(String... args) throws Exception {
//        jdbcTemplate.execute("INSERT INTO Account (id, name, email, bill)" +
//                                    "VALUES (1, 'Lori', 'lori@cat.ua', 2000)");
//
//        Map<String, Object> resultSet = jdbcTemplate.queryForMap("SELECT * from Account");
//        System.out.println(resultSet.get("email"));
//        Account accountById = findAccountById(1L);
//        System.out.println(accountById);
        for (Long i = Long.valueOf(1); i < 10; i++) {
            accountRepository.save(new Account(null, "Lori" + i, "lori@cat.ua", (int) (2000*i)));
        }

        System.out.println(accountRepository.findAccountByBill(4000));
        System.out.println(accountRepository.findAccountByName("Lori5"));
        System.out.println(accountRepository.findAccountByNameAndBill("Lori6", 12000+));
    }
    private Account findAccountById(Long accountId) {
        String query = "SELECT * FROM Account WHERE id=%s";
        Map<String, Object> resultSet = jdbcTemplate.queryForMap(String.format(query, accountId));
        Account account = new Account();
        account.setId(accountId);
        account.setName((String) resultSet.get("name"));
        account.setEmail((String) resultSet.get("email"));
        account.setBill((Integer) resultSet.get("bill"));
        return account;
    }
}
