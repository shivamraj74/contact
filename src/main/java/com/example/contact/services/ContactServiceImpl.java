@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public Map<String, Object> identifyContact(Map<String, Object> requestData) {
        // Extract email and phoneNumber from the request data
        String email = (String) requestData.get("email");
        String phoneNumber = String.valueOf(requestData.get("phoneNumber"));

        // Retrieve the existing primary contact based on email or phoneNumber
        Contact primaryContact = null;
        if (email != null) {
            primaryContact = contactRepository.findByEmailAndLinkPrecedence(email, "primary");
        }
        if (primaryContact == null && phoneNumber != null) {
            primaryContact = contactRepository.findByPhoneNumberAndLinkPrecedence(phoneNumber, "primary");
        }

        if (primaryContact == null) {
            // Create a new primary contact if it doesn't exist
            primaryContact = new Contact();
            primaryContact.setEmail(email);
            primaryContact.setPhoneNumber(phoneNumber);
            primaryContact.setLinkPrecedence("primary");
            primaryContact = contactRepository.save(primaryContact);
        } else {
            // Check if the primary contact needs to be converted to a secondary contact
            if (!primaryContact.getEmail().equals(email) || !primaryContact.getPhoneNumber().equals(phoneNumber)) {
                // Create a new secondary contact with the previous primary contact's information
                Contact secondaryContact = new Contact();
                secondaryContact.setEmail(primaryContact.getEmail());
                secondaryContact.setPhoneNumber(primaryContact.getPhoneNumber());
                secondaryContact.setLinkPrecedence("secondary");
                secondaryContact.setLinkedId(primaryContact.getId());
                secondaryContact = contactRepository.save(secondaryContact);

                // Update the primary contact with the new information
                primaryContact.setEmail(email);
                primaryContact.setPhoneNumber(phoneNumber);
                primaryContact.setLinkPrecedence("secondary");
                primaryContact.setLinkedId(secondaryContact.getId());
                primaryContact = contactRepository.save(primaryContact);
            }
        }

        // Retrieve all secondary contacts related to the primary contact
        List<Contact> secondaryContacts = contactRepository.findByLinkedId(primaryContact.getId());

        // Create the response payload
        Map<String, Object> response = new HashMap<>();
        Map<String, Object> contactData = new HashMap<>();
        contactData.put("primaryContactId", primaryContact.getId());
        contactData.put("emails", Collections.singletonList(primaryContact.getEmail()));
        contactData.put("phoneNumbers", Collections.singletonList(primaryContact.getPhoneNumber()));
        List<Long> secondaryContactIds = secondaryContacts.stream().map(Contact::getId).collect(Collectors.toList());
        contactData.put("secondaryContactIds", secondaryContactIds);
        response.put("contact", contactData);

        return response;
    }
}
