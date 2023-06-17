@RestController
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/identify")
    public ResponseEntity<Map<String, Object>> identifyContact(@RequestBody(required = false) Map<String, Object> requestData) {
        Map<String, Object> response = contactService.identifyContact(requestData);
        return ResponseEntity.ok(response);
    }
}
