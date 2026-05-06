package payloadBuilder;

public class TestimonialPayload {


    public static String createTestimonialPayload() {
        return "{\n" +
                "  \"title\": \"Great Learning Experience\",\n" +
                "  \"content\": \"The course was well structured and easy to follow. The practical examples made it easier to understand key concepts, especially in automation. I would highly recommend it to anyone starting out.\",\n" +
                "  \"rating\": 5,\n" +
                "  \"isPublic\": true\n" +
                "}";
    }

        public static String updateTestimonialPayload () {
            return "{\n" +
                    "  \"title\": \"Perfect for Beginners in Automation\",\n" +
                    "  \"content\": \"This course provided a solid foundation in automation testing. The explanations were clear, and the hands-on exercises helped me understand real-world scenarios. It’s a great starting point for anyone new to automation.\",\n" +
                    "  \"rating\": 5\n" +
                    "}";
        }
    }
