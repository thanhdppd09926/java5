package ass.ass.services;
// package ass.ass.service;

// import ass.ass.models.Accounts;
// import ass.ass.repository.AccountRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.List;
// import java.util.Optional;

// @Service
// public class AccountService {

// @Autowired
// private AccountRepository accountRepository;

// // Tìm tất cả tài khoản
// public List<Accounts> findAll() {
// return accountRepository.findAll();
// }

// // Lưu tài khoản mới
// public Accounts save(Accounts account) {
// return accountRepository.save(account);
// }

// // Cập nhật tài khoản
// public Accounts update(Long id, Accounts account) {
// Optional<Accounts> existingAccount = accountRepository.findById(id);
// if (existingAccount.isPresent()) {
// Accounts updatedAccount = existingAccount.get();
// updatedAccount.setUsername(account.getUsername());
// updatedAccount.setFullname(account.getFullname());
// updatedAccount.setEmail(account.getEmail());
// updatedAccount.setActivated(account.isActivated());
// return accountRepository.save(updatedAccount);
// } else {
// throw new RuntimeException("Tài khoản không tồn tại");
// }
// }

// // Xóa tài khoản
// public void delete(Long id) {
// accountRepository.deleteById(id);
// }
// }