package explore.ollamaSpring.controller;
import explore.ollamaSpring.service.OllamaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/ask")
public class OllamaController {
    @Autowired
    OllamaService ollamaService;

    @PostMapping("/prompt")
    public String getResponse(@RequestBody String prompt)
    {
        return ollamaService.getResponse(prompt);
    }
    @PostMapping("/jsonOutput")
    public String  getJsonResponse(@RequestParam String category, int year)
    {
        return ollamaService.getJsonResponse(category,year);
    }

    @GetMapping("/image")
    public String getImageResponse() throws IOException
    {
        return ollamaService.getImageResponse();
    }
}
