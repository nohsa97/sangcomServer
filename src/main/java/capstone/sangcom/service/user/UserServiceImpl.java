package capstone.sangcom.service.user;

import capstone.sangcom.entity.UserDTO;
import capstone.sangcom.entity.dto.userSection.info.UpdateUserInfoDTO;
import capstone.sangcom.entity.User;
import capstone.sangcom.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO getUserInfo(String id) {
        User user = userRepository.findById(id);
        return new UserDTO(user);
    }
    @Override
    public boolean editProfile(String id) {
        return false;
    }

    @Override
    public boolean editPassword(String id, String newPassword) {
        String encodePassword = passwordEncoder.encode(newPassword);

        return userRepository.update(id, encodePassword);
    }

    @Override
    @Transactional
    public boolean editUserInfo(String id, UpdateUserInfoDTO updateUserInfoDTO) {
        return userRepository.update(id, updateUserInfoDTO);
    }

    @Override
    public boolean leave(String id) {
        return userRepository.delete(id);
    }
}
