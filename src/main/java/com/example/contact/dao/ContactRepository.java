import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByEmailAndLinkPrecedence(String email, String linkPrecedence);

    Contact findByPhoneNumberAndLinkPrecedence(String phoneNumber, String linkPrecedence);

    List<Contact> findByLinkedId(Long linkedId);
}
