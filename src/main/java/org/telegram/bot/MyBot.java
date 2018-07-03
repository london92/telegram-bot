package org.telegram.bot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendAudio;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyBot extends TelegramLongPollingBot {

    public String getBotToken() {
        return "592768101:AAFdVsWUjig6PXZG21bKyFd0KTb3lCSkOBY";
    }

    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();

        SendMessage message = new SendMessage().setChatId(chatId);

        if (update.hasMessage() && text.equals("/markup")) {
            message.setText("Your markup!");
            setMarkup(message);
        }
        else if(update.hasMessage() && text.equals("/hide")){
            message.setText("Markup is removed!");
            hideMarkup(message);
        }
        else {
            message.setText(EmojiParser.parseToUnicode(":heart:"));
            SendAudio audio = new SendAudio().setChatId(chatId);
            audio.setAudio("trombone");
            try {
                sendAudio(audio);
            } catch (Exception e) {

            }
        }

        try {
            execute(message);// Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getBotUsername() {
        return "u34ra1neeeeeeee123_bot";
    }

    private SendMessage setMarkup (SendMessage message) {
        // Create ReplyKeyboardMarkup object
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        // Create the keyboard (list of keyboard rows)
        List<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();
        // Create a keyboard row
        KeyboardRow row = new KeyboardRow();
        // Set each button, you can also use KeyboardButton objects if you need something else than text
        row.add("Row 1 Button 1");
        row.add("Row 1 Button 2");
        row.add("Row 1 Button 3");
        // Add the first row to the keyboard
        keyboard.add(row);
        // Create another keyboard row
        row = new KeyboardRow();
        // Set each button for the second line
        row.add("Row 2 Button 1");
        row.add("Row 2 Button 2");
        row.add("Row 2 Button 3");
        // Add the second row to the keyboard
        keyboard.add(row);
        // Set the keyboard to the markup
        keyboardMarkup.setKeyboard(keyboard);
        // Add it to the message
        return message.setReplyMarkup(keyboardMarkup);
    }

    private SendMessage hideMarkup(SendMessage message) {
        ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();

        return message.setReplyMarkup(keyboardMarkup);
    }
}
