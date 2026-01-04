package ui;

import java.util.Collections;
import java.util.Random;

import model.Author;
import model.AuthorList;
import model.Quote;
import model.QuoteList;

public class QuoteAndOptionsPicker {
    private QuoteList quotes;
    private AuthorList authors;

    public QuoteAndOptionsPicker(QuoteList quotes, AuthorList authors) {
        this.quotes = quotes;
        this.authors = authors;
    }

      // EFFECTS: pick the quotes that the players need to quess its author
    public Quote quotePicker(QuoteList quoteList) {
        Random options = new Random();
        if (quotes.getSize() == 0) {
            return null;
        } else {
            Quote pickedQuote = quoteList.getQuote(options.nextInt(quotes.getSize()));
            return pickedQuote;
        }
    }

    // EFFECTS: provide the options to the players
    public AuthorList optionProvider(Quote quote) {
        if (quote == null) {
            return null;
        } else {
            AuthorList firstOptions = new AuthorList("");
            AuthorList finalOptions = new AuthorList("");
            Author answer = new Author(quote.getAnswer());

            for (int i = 0; i < 3; i++) {
                randomPicking(firstOptions, answer);
            }

            for (int i = 0; i < firstOptions.getSize(); i++) {
                authors.addAuthor(firstOptions.getAuthor(i));
            }

            finalOptions.addAuthor(answer);
            finalOptions.addAuthor(firstOptions.getAuthor(0));
            finalOptions.addAuthor(firstOptions.getAuthor(1));
            finalOptions.addAuthor(firstOptions.getAuthor(2));

            Collections.shuffle(finalOptions.getAuthorList());

            // authors.addAuthor(answer);

            return finalOptions;
        }
    }

    // EFFECTS: pick random authors in the AuthorList as options
    private void randomPicking(AuthorList optionList, Author answer) {
        Random options = new Random();
        int index = options.nextInt(authors.getSize());

        // Author same = new Author("Same");
        Author picked = authors.getAuthor(index);
        if (picked.getName().compareTo(answer.getName()) == 0) {
            randomPicking(optionList, answer);
        } else {
            optionList.addAuthor(picked);
            authors.removeAuthor(picked.getName());
        }
    }
}
