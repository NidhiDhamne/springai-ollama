package explore.ollamaSpring.service;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.Media;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;
import java.util.List;

@Service
public class OllamaService {
    @Autowired
    OllamaChatModel ollamaChatModel;
    public String getResponse(String prompt)
    {
        return ollamaChatModel.call(prompt);
    }
    public String getJsonResponse(String category, int year)
    {
        String template= """
                Provide me with book recommendations based on the {category} and {year}
                Return the response in a JSON format with parameters : name, year, author, version, summary
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        promptTemplate.add("category",category);
        promptTemplate.add("year", year);
        Prompt prompt = promptTemplate.create();
        return ollamaChatModel.call(prompt).getResult().getOutput().getContent();

    }
    public String getImageResponse() throws IOException
    {
        byte[] ImageData = new ClassPathResource("/image.jpeg").getContentAsByteArray();
        var message = new UserMessage("Describe the landscape. What country is this located in? What are the unique characteristics of this architectural wonder", new Media(MimeTypeUtils.IMAGE_JPEG, ImageData));
        return ollamaChatModel.call(new Prompt(message)).getResult().getOutput().getContent();

    }


}
