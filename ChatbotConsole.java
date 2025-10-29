import java.util.*;

public class ChatbotConsole {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("🤖 Hello! I’m ChatBot. Type 'bye' to exit.");

        while (true) {
            System.out.print("You: ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                System.out.println("ChatBot: Goodbye! 👋");
                break;
            }
            System.out.println("ChatBot: " + respond(input));
        }
    }

    static String respond(String input) {
        if (input.contains("hello") || input.contains("hi"))
            return "Hi there! How can I help you today?";
        if (input.contains("your name"))
            return "I'm a simple Java chatbot built for your programming task.";
        if (input.contains("weather"))
            return "I can’t check live weather, but I hope it’s sunny where you are!";
        if (input.contains("time"))
            return "The current time is " + new Date().toString();
        if (input.contains("help"))
            return "I can answer basic greetings, tell you time, or chat casually.";
        return "Sorry, I don’t understand that yet.";
    }
}