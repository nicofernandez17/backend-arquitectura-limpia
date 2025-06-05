package utn.services.spamDetector.akismet;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import utn.configs.AkismetProperties;

@Service
public class AkismetService {

    private final RestTemplate restTemplate;
    private final AkismetProperties properties;

    public AkismetService(RestTemplateBuilder builder, AkismetProperties properties) {
        this.restTemplate = builder.build();
        this.properties = properties;
    }

    public boolean isSpam(String content, String userIp, String userAgent) {
        MultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("blog", properties.getBlogUrl());
        request.add("user_ip", "127.0.0.1");
        request.add("user_agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)");
        request.add("comment_content", content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(request, headers);

        String url = "https://" + properties.getApiKey() + ".rest.akismet.com/1.1/comment-check";
        String response = restTemplate.postForObject(url, entity, String.class);

        return Boolean.parseBoolean(response);
    }
}