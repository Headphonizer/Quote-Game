package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Random;

import model.Author;
import model.AuthorList;
import model.EventLog;
import model.Quote;
import model.QuoteList;
import model.Score;
import persistence.*;

import java.util.Collections;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.*;

//CITATION: parts of this code is based on JsonSerializationDemo

public class QuoteGame extends JFrame implements ActionListener {
    private static final String POLITICS_QUOTE = "./data/Politics/politicsQuote.json";
    private static final String POLITICS_AUTHOR = "./data/Politics/politicsAuthor.json";
    private static final String POLITICS_SCORE = "./data/Politics/politicsScore.json";


    private static final String TECH_QUOTE = "./data/Tech/techQuote.json";
    private static final String TECH_AUTHOR = "./data/Tech/techAuthor.json";
    private static final String TECH_SCORE = "./data/Tech/techScore.json";


    private static final String MARVEL_QUOTE = "./data/Marvel/marvelQuote.json";
    private static final String MARVEL_AUTHOR = "./data/Marvel/marvelAuthor.json";
    private static final String MARVEL_SCORE = "./data/Marvel/marvelScore.json";


    private static final String DEMON_QUOTE = "./data/Demon Slayer/demonQuote.json";
    private static final String DEMON_AUTHOR = "./data/Demon Slayer/demonAuthor.json";
    private static final String DEMON_SCORE = "./data/Demon Slayer/demonScore.json";


    private static final String DC_QUOTE = "./data/DC/dcQuote.json";
    private static final String DC_AUTHOR = "./data/DC/dcAuthor.json";
    private static final String DC_SCORE = "./data/DC/dcScore.json";


    private static final String STAR_WARS_QUOTE = "./data/Star Wars/starwarsQuote.json";
    private static final String STAR_WARS_AUTHOR = "./data/Star Wars/starwarsAuthor.json";
    private static final String STAR_WARS_SCORE = "./data/Star Wars/starwarsScore.json";


    private static final String HARRY_POTTER_QUOTE = "./data/Harry Potter/harrypotterQuote.json";
    private static final String HARRY_POTTER_AUTHOR = "./data/Harry Potter/harrypotterAuthor.json";
    private static final String HARRY_POTTER_SCORE = "./data/Harry Potter/harrypotterScore.json";


    private static final String POP_QUOTE = "./data/Pop/popQuote.json";
    private static final String POP_AUTHOR = "./data/Pop/popAuthor.json";
    private static final String POP_SCORE = "./data/Pop/popScore.json";

    
    private Quote chosenQuote;
    private AuthorList chosenOptions;
    private QuoteList quotes;
    private AuthorList authors;
    private Score score;
    private QuoteAndOptionsPicker gamePicker;
    private JsonWriter jsonQuoteWriter;
    private JsonReader jsonQuoteReader;
    private JsonWriter jsonAuthorWriter;
    private JsonReader jsonAuthorReader;
    private JsonWriter jsonScoreWriter;
    private JsonReader jsonScoreReader;
    private String quoteCategory = "";
    private String authorCategory = "";
    private String scoreCategory = "";
    private JFrame gameFrame;

    // EFFECTS: run the quote game
    public QuoteGame() throws FileNotFoundException {
        getFeeling();
        init();
    }

    // ***************************************************************************/

    // Main UI of the game

    // EFFECTS: initialize the game, creates new frame, and call chooseThemes function
    private void init() {
        gameFrame = new JFrame();
        gameFrame.setTitle("The Great Quote Game");
        gameFrame.setSize(1500, 800);
        //gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gameFrame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Game has ended!");
                printlog(EventLog.getInstance());
                System.exit(0);
            }
        });

        chooseThemes();
    }

    // EFFECTS: choose the themes of the game
    @SuppressWarnings("methodlength")
    private void chooseThemes() {
        JPanel init = new JPanel();
        configurePanel(init);

        JLabel instructionLabel = new JLabel("Please pick your prefered theme:");
        configureLabel(instructionLabel);
        instructionLabel.setFont(futuristicFontFunction(48));
        init.add(instructionLabel);

        JPanel buttons = new JPanel();
        configurePanel(buttons);

        GridBagConstraints gbc = new GridBagConstraints();

        // gbc.insets = new Insets(0, 100, 0, 100);

        JButton politicsButton = new JButton("Politics");
        configureButton(politicsButton);

        JButton techButton = new JButton("Tech/Engineering");
        configureButton(techButton);

        JButton marvelButton = new JButton("Marvel");
        configureButton(marvelButton);

        JButton demonSlayerButton = new JButton("Demon Slayer");
        configureButton(demonSlayerButton);

        JButton dcButton = new JButton("DC");
        configureButton(dcButton);

        JButton starWarsButton = new JButton("Star Wars");
        configureButton(starWarsButton);

        JButton harryPotterButton = new JButton("Harry Potter");
        configureButton(harryPotterButton);

        JButton popButton = new JButton("Celebrities");
        configureButton(popButton);

        buttons.add(politicsButton, (GridBagConstraints) gbc.clone());
        buttons.add(techButton, (GridBagConstraints) gbc.clone());
        buttons.add(marvelButton, (GridBagConstraints) gbc.clone());
        buttons.add(demonSlayerButton, (GridBagConstraints) gbc.clone());
        buttons.add(dcButton, (GridBagConstraints) gbc.clone());
        buttons.add(starWarsButton, (GridBagConstraints) gbc.clone());
        buttons.add(harryPotterButton, (GridBagConstraints) gbc.clone());
        buttons.add(popButton, (GridBagConstraints) gbc.clone());

        politicsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(POLITICS_QUOTE, POLITICS_AUTHOR, POLITICS_SCORE);
            }
        });

        techButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(TECH_QUOTE, TECH_AUTHOR, TECH_SCORE);
            }
        });

        marvelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(MARVEL_QUOTE, MARVEL_AUTHOR, MARVEL_SCORE);
            }
        });

        demonSlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(DEMON_QUOTE, DEMON_AUTHOR, DEMON_SCORE);
            }
        });

        dcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(DC_QUOTE, DC_AUTHOR, DC_SCORE);
            }
        });

        starWarsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(STAR_WARS_QUOTE, STAR_WARS_AUTHOR, STAR_WARS_SCORE);
            }
        });

        harryPotterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(HARRY_POTTER_QUOTE, HARRY_POTTER_AUTHOR, HARRY_POTTER_SCORE);
            }
        });

        popButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executeChosenThemes(POP_QUOTE, POP_AUTHOR, POP_SCORE);
            }
        });

        modifyPosition(buttons, 30, 80, 0, 80);

        JPanel container = new JPanel(new BorderLayout());
        container.add(init, BorderLayout.PAGE_START);
        container.add(neonSpacing(), BorderLayout.CENTER);

        JPanel south = new JPanel(new BorderLayout());
        south.add(container, BorderLayout.PAGE_START);
        south.add(buttons, BorderLayout.CENTER);

        gameFrame.add(south);
    }

    // EFFECTS: load the JSON files of the chosen theme
    private void executeChosenThemes(String quote, String author, String score) {
        jsonQuoteWriter = new JsonWriter(quote);
        jsonQuoteReader = new JsonReader(quote);
        jsonAuthorWriter = new JsonWriter(author);
        jsonAuthorReader = new JsonReader(author);
        jsonScoreWriter = new JsonWriter(score);
        jsonScoreReader = new JsonReader(score);
        loadQuoteList(quote);
        loadAuthorList(author);
        loadScore(score);
        quoteCategory = quote;
        authorCategory = author;
        scoreCategory = score;
        gamePicker = new QuoteAndOptionsPicker(quotes, authors);
        chosenQuote = gamePicker.quotePicker(quotes);
        chosenOptions = gamePicker.optionProvider(chosenQuote);
        removeEverything();
        displayOptions(chosenQuote, chosenOptions);
    }

    // EFFECTS: displays menu of options to the players
    @SuppressWarnings("methodlength")
    private void displayOptions(Quote quote, AuthorList options) {
        JPanel upperInfo = new JPanel(new BorderLayout());
        configurePanel(upperInfo);

        JLabel instructionLabel = new JLabel("Who said this?:");
        configureLabel(instructionLabel);
        instructionLabel.setFont(futuristicFontFunction(24));
        instructionLabel.setLayout(new FlowLayout(FlowLayout.LEFT));
        upperInfo.add(instructionLabel);

        JPanel quoteInfo = new JPanel(new BorderLayout());
        configurePanel(quoteInfo);
        JTextArea quoteLabel = new JTextArea(quote.getQuote(), 2, 30);
        configureTextArea(quoteLabel);
        quoteLabel.setFont(futuristicFontFunction(32));
        quoteInfo.add(quoteLabel);
        modifyPosition(quoteInfo, 20, 40, 20, 40);

        JPanel optionPanel = new JPanel(new BorderLayout());
        configurePanel(optionPanel);

        GridBagConstraints gbc = new GridBagConstraints();

        JButton abutton = new JButton("\tA -> " + options.getAuthor(0).getName());
        optionPanel.add(abutton);
        configureButton(abutton);

        JButton bbutton = new JButton("\tB -> " + options.getAuthor(1).getName());
        optionPanel.add(bbutton);
        configureButton(bbutton);

        JButton cbutton = new JButton("\tC -> " + options.getAuthor(2).getName());
        optionPanel.add(cbutton);
        configureButton(cbutton);

        JButton dbutton = new JButton("\tD -> " + options.getAuthor(3).getName());
        optionPanel.add(dbutton);
        configureButton(dbutton);

        optionPanel.add(abutton, (GridBagConstraints) gbc.clone());
        // gbc.gridx = 1;
        optionPanel.add(bbutton, (GridBagConstraints) gbc.clone());
        // gbc.gridx = 2;
        optionPanel.add(cbutton, (GridBagConstraints) gbc.clone());
        // gbc.gridx = 3;
        optionPanel.add(dbutton, (GridBagConstraints) gbc.clone());

        JPanel scorePanel = new JPanel();
        configurePanel(scorePanel);
        JLabel scoreLabel = new JLabel("\nScore: " + score.getScore());
        configureLabel(scoreLabel);
        scoreLabel.setFont(futuristicFontFunction(30));
        scorePanel.add(scoreLabel);

        JPanel numberPanel = new JPanel();
        configurePanel(numberPanel);
        JLabel numberLabel = new JLabel("\nNumber of quotes: " + quotes.getSize());
        configureLabel(numberLabel);
        numberLabel.setFont(futuristicFontFunction(30));
        numberPanel.add(numberLabel);

        JPanel functionPanel = new JPanel(new BorderLayout());
        configurePanel(functionPanel);

        JButton createButton = new JButton("Create quote!");
        functionPanel.add(createButton);
        configureButton(createButton);

        JButton viewButton = new JButton("View quotes");
        functionPanel.add(viewButton);
        configureButton(viewButton);

        JButton saveButton = new JButton("Save Progress");
        functionPanel.add(saveButton);
        configureButton(saveButton);

        JButton changeButton = new JButton("Change theme");
        functionPanel.add(changeButton);
        configureButton(changeButton);

        modifyPosition(functionPanel, 30, 80, 0, 80);

        modifyPosition(numberPanel, 20, 80, 30, 80);

        abutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                checkAnswer(options.getAuthor(0).getName(), quote);
            }
        });

        bbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                checkAnswer(options.getAuthor(1).getName(), quote);
            }
        });

        cbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                checkAnswer(options.getAuthor(2).getName(), quote);
            }
        });

        dbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                checkAnswer(options.getAuthor(3).getName(), quote);
            }
        });

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                addQuote();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                viewAll();
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveQuoteList(quoteCategory);
                saveAuthorList(authorCategory);
                saveScore(scoreCategory);
            }
        });

        changeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                chooseThemes();
            }
        });

        JPanel container = new JPanel(new BorderLayout());
        container.add(upperInfo, BorderLayout.PAGE_START);
        container.add(quoteInfo, BorderLayout.CENTER);

        JPanel container2 = new JPanel(new BorderLayout());
        container2.add(container, BorderLayout.PAGE_START);
        container2.add(optionPanel, BorderLayout.CENTER);
        container.add(neonSpacing(), BorderLayout.PAGE_END);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(container2, BorderLayout.PAGE_START);
        mainPanel.add(functionPanel, BorderLayout.CENTER);
        mainPanel.add(scorePanel, BorderLayout.PAGE_END);

        JPanel finalPanel = new JPanel(new BorderLayout());
        finalPanel.add(mainPanel, BorderLayout.CENTER);
        finalPanel.add(numberPanel, BorderLayout.PAGE_END);

        gameFrame.add(finalPanel);

        scrolling(finalPanel);
    }

    // ***************************************************************************/

    // Main functions of the game

    // MODIFIES: this
    // EFFECTS: check if the players choose the right answer
    private void checkAnswer(String option, Quote quote) {
        if (option.equals("Evan")) {
            score.addLoggedSuperScore(quotes.getCategory());
            checkAnswerFunction("Thanks for choosing the God of this game!", false, null);
        } else if (option.equals(quote.getAnswer())) {
            score.addLoggedScore(quotes.getCategory());
            checkAnswerFunction("Congratulations! Add 10 points", false, null);
        } else {
            score.resetLoggedScore(quotes.getCategory());
            checkAnswerFunction("You're not smart, you don't deserve any points!", true, quote);
        }
    }

    // EFFECTS: creates the UI that tells the players whether they're right or not
    @SuppressWarnings("methodlength")
    private void checkAnswerFunction(String message, Boolean answer, Quote quote) {
        JPanel messagePanel = new JPanel(new BorderLayout());
        configurePanel(messagePanel);

        JLabel messageLabel = new JLabel(message);
        configureLabel(messageLabel);
        messageLabel.setFont(futuristicFontFunction(40));
        messagePanel.add(messageLabel);

        JPanel emojiPanel = new JPanel(new BorderLayout());
        configurePanel(emojiPanel);

        modifyPosition(emojiPanel, 20, 0, 0, 0);

        
        JPanel answerPanel = new JPanel(new BorderLayout());
        configurePanel(answerPanel);

        JLabel answerLabel = new JLabel();
        configureLabel(answerLabel);
        if (answer) {
            answerLabel.setText("Correct answer: " + quote.getAnswer());
        }
        answerLabel.setFont(futuristicFontFunction(40));
        answerPanel.add(answerLabel);
        modifyPosition(answerPanel, 0, 0, 40, 0);

        JPanel scorePanel = new JPanel(new BorderLayout());
        configurePanel(scorePanel);

        JLabel scoreLabel = new JLabel("Score: " + score.getScore());
        configureLabel(scoreLabel);
        scoreLabel.setFont(futuristicFontFunction(40));
        scorePanel.add(scoreLabel);

        JPanel continuePanel = new JPanel(new BorderLayout());
        configurePanel(continuePanel);

        JButton continueButton = new JButton("Continue");
        configureButton(continueButton);
        continuePanel.add(continueButton);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                chosenQuote = gamePicker.quotePicker(quotes);
                chosenOptions = gamePicker.optionProvider(chosenQuote);
                displayOptions(chosenQuote, chosenOptions);
            }
        });

        modifyPosition(messagePanel, 50, 80, 0, 80);

        modifyPosition(continuePanel, 30, 80, 80, 80);

        JPanel infoPanel = new JPanel(new BorderLayout());
        infoPanel.add(messagePanel, BorderLayout.PAGE_START);
        infoPanel.add(emojiPanel, BorderLayout.CENTER);
        infoPanel.add(answerPanel, BorderLayout.PAGE_END);
        

        JPanel correctPanel = new JPanel(new BorderLayout());
        correctPanel.add(infoPanel, BorderLayout.PAGE_START);
        correctPanel.add(scorePanel, BorderLayout.CENTER);
        correctPanel.add(continuePanel, BorderLayout.PAGE_END);

        gameFrame.add(correctPanel);
    }

    // MODIFIES: this
    // EFFECTS: enable users to add their favorite quotes to this game
    @SuppressWarnings("methodlength")
    private void addQuote() {
        JPanel quotePanel = new JPanel(new BorderLayout());
        configurePanel(quotePanel);

        JLabel instructionLabel1 = new JLabel("Please type your quote: ");
        instructionLabel1.setFont(futuristicFontFunction(32));
        configureLabel(instructionLabel1);
        quotePanel.add(instructionLabel1);

        JTextField quoteField = new JTextField(30);
        configureTextField(quoteField);
        quotePanel.add(quoteField);

        modifyPosition(quotePanel, 80, 80, 80, 80);

        JPanel authorPanel = new JPanel(new BorderLayout());
        configurePanel(authorPanel);

        JLabel instructionLabel2 = new JLabel("Please type its author: ");
        instructionLabel2.setFont(futuristicFontFunction(32));
        configureLabel(instructionLabel2);
        authorPanel.add(instructionLabel2);

        JTextField authorField = new JTextField(30);
        configureTextField(authorField);
        authorPanel.add(authorField);

        JPanel submitPanel = new JPanel(new BorderLayout());
        configurePanel(submitPanel);

        JButton submitButton = new JButton("Submit");
        configureButton(submitButton);
        submitPanel.add(submitButton);

        JButton returnButton = new JButton("return");
        configureButton(returnButton);
        submitPanel.add(returnButton);

        modifyPosition(submitPanel, 30, 80, 80, 80);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isSame = false;
                String quoteString = quoteField.getText();
                String authorName = authorField.getText();
                Quote newQuote = new Quote(quoteString, authorName);
                quotes.addLoggedQuote(newQuote, quotes.getCategory());
                for (Author a : authors.getAuthorList()) {
                    if (authorName.equals(a.getName())) {
                        isSame = true;
                    }
                }

                if (!isSame) {
                    authors.addAuthor(new Author(authorName));
                }

                removeEverything();
                displayOptions(chosenQuote, chosenOptions);
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                displayOptions(chosenQuote, chosenOptions);
            }
        });

        JPanel south = new JPanel(new BorderLayout());
        south.add(quotePanel, BorderLayout.PAGE_START);
        south.add(authorPanel, BorderLayout.CENTER);
        south.add(submitPanel, BorderLayout.PAGE_END);

        gameFrame.add(south);
    }

    // EFFECTS: access all the quotes and authors
    @SuppressWarnings("methodlength")
    private void viewAll() {
        JPanel instructionPanel = new JPanel(new BorderLayout());
        configurePanel(instructionPanel);

        JLabel instructionLabel = new JLabel("Here's the entire information of the game");
        configureLabel(instructionLabel);
        instructionLabel.setFont(futuristicFontFunction(24));
        instructionPanel.add(instructionLabel);

        JPanel infoPanel = new JPanel();
        configurePanel(infoPanel);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        for (int i = 0; i < quotes.getSize(); i++) {
            JPanel quotePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            quotePanel.setBackground(Color.BLACK);
            JLabel quoteLabel = new JLabel("\tQuote: " + quotes.getQuote(i).getQuote());
            quoteLabel.setFont(futuristicFontFunction(16));
            configureLabel(quoteLabel);
            quotePanel.add(quoteLabel);
            infoPanel.add(quotePanel);

            JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            authorPanel.setBackground(Color.BLACK);
            JLabel authorLabel = new JLabel("\tAnswer: " + quotes.getQuote(i).getAnswer());
            authorLabel.setFont(futuristicFontFunction(16));
            configureLabel(authorLabel);
            authorPanel.add(authorLabel);
            infoPanel.add(authorPanel);

            infoPanel.add(neonSpacing());
        }

        JPanel continuePanel = new JPanel(new GridBagLayout());
        configurePanel(continuePanel);

        JButton continueButton = new JButton("Resume Game");
        configureButton(continueButton);
        continuePanel.add(continueButton);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeEverything();
                displayOptions(chosenQuote, chosenOptions);
            }
        });

        JPanel viewPanel = new JPanel(new BorderLayout());
        viewPanel.add(instructionPanel, BorderLayout.PAGE_START);
        viewPanel.add(infoPanel, BorderLayout.CENTER);
        viewPanel.add(continuePanel, BorderLayout.PAGE_END);

        // gameFrame.add(viewPanel);

        gameFrame.add(viewPanel, BorderLayout.WEST);

        scrolling(viewPanel);
    }

    // ***************************************************************************/

    // helper functions:

    // EFFECTS: sets the overall style of the UI
    private void getFeeling() {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EFFECTS: standardize the labels 
    public void configureLabel(JLabel label) {
        label.setForeground(new Color(0, 240, 255));
    }

    // EFFECTS: load futuristic fonts
    private Font futuristicFontFunction(float size) {
        return FuturisticFonts.loadFont("/Resources/Orbitron-Bold.ttf", size);
    }

    // EFFECTS: standardize the buttons 
    @SuppressWarnings("methodlength")
    public void configureButton(JButton button) {
        Color color = new Color(0, 240, 255);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                button.repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                button.repaint();
            }
        });

        JButton finalBtn = button;
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            protected void paintButtonPressed(Graphics g, AbstractButton b) {

            }

            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int w = c.getWidth();
                int h = c.getHeight();
                boolean hover = finalBtn.getModel().isRollover();

                if (hover) {
                    g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 200));
                    g2.fillRoundRect(-4, -4, w + 8, h + 8, 50, 50);
                    g2.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
                    g2.fillRoundRect(-2, -2, w + 4, h + 4, 50, 50);
                }

                g2.setColor(new Color(83, 104, 120, 220));
                g2.fillRoundRect(0, 0, w, h, 20, 20);

                g2.setColor(color);
                String text = ((JButton) c).getText();
                FontMetrics fm = g2.getFontMetrics();
                int x = (w - fm.stringWidth(text)) / 2;
                int y = (h + fm.getAscent()) / 2 - 2;
                g2.drawString(text, x, y);

                g2.dispose();
            }
        });

        button.setPreferredSize(new Dimension(300, 100));
        button.setFont(FuturisticFonts.loadFont("/Resources/Orbitron-SemiBold.ttf", 20f));
    }

    // EFFECTS: standardize the panels
    public void configurePanel(JPanel panel) {
        panel.setBackground(Color.BLACK);
        panel.setLayout(new FlowLayout());
        gameFrame.add(panel);
    }

    // EFFECTS: standardize the text areas 
    private void configureTextArea(JTextArea area) {
        area.setForeground(new Color(0, 240, 255));
        area.setBackground(Color.BLACK);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setBorder(BorderFactory.createEtchedBorder());
    }

    // EFFECTS: standardize the text fields
    private void configureTextField(JTextField field) {
        field.setFont(FuturisticFonts.loadFont("/Resources/Orbitron-Bold.ttf", 24f));
        field.setBackground(new Color(0, 240, 255));
        field.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    }

    // EFFECTS: remove every object of the UI. Call it when change the web page
    private void removeEverything() {
        gameFrame.getContentPane().removeAll();
        gameFrame.getContentPane().revalidate();
        gameFrame.getContentPane().repaint();
    }

    // EFFECTS: configure the position of an object
    private void modifyPosition(JPanel panel, int top, int left, int bottom, int right) {
        panel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        panel.revalidate();
        panel.repaint();
    }

    // EFFECTS: creates a neon spacing between objects 
    private JPanel neonSpacing() {
        JPanel neonSpacer = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(4, 217, 255));
                int y = getHeight() / 2;
                for (int i = -2; i <= 2; i++) {
                    g2d.drawLine(100, y + i, getWidth() - 100, y + i);
                }
            }
        };
        neonSpacer.setPreferredSize(new Dimension(0, 40));
        neonSpacer.setBackground(new Color(5, 5, 15));

        return neonSpacer;
    }

    // EFFECTS: creates a scrolling device
    private void scrolling(JPanel panel) {
        JScrollPane scrollingTool = new JScrollPane(panel);
        scrollingTool.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        gameFrame.add(scrollingTool);
        gameFrame.setVisible(true);
    }

    // ***************************************************************************/

    // FIle saving/loading

    // EFFECTS: saves the Quote List to file
    private void saveQuoteList(String list) {
        try {
            jsonQuoteWriter.open();
            jsonQuoteWriter.writeQuote(quotes);
            jsonQuoteWriter.close();
            System.out.println("Saved progress to " + list);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + list);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Quote List from file
    private void loadQuoteList(String list) {
        try {
            quotes = jsonQuoteReader.readQuotes();
            System.out.println("Loaded quoteList from " + list);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + list);
        }
    }

    // EFFECTS: saves the AuthorList to file
    private void saveAuthorList(String list) {
        try {
            jsonAuthorWriter.open();
            jsonAuthorWriter.writeAuthor(authors);
            jsonAuthorWriter.close();
            System.out.println("Saved authorList to " + list);
            System.out.println("----------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + list);
            System.out.println("----------------------------------");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads AuthorList from file
    private void loadAuthorList(String list) {
        try {
            authors = jsonAuthorReader.readAuthors();
            System.out.println("Loaded authorList from " + list);
            System.out.println("----------------------------------");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + list);
            System.out.println("----------------------------------");
        }
    }

    // EFFECTS: saves the AuthorList to file
    private void saveScore(String list) {
        try {
            jsonScoreWriter.open();
            jsonScoreWriter.writeScore(score);
            jsonScoreWriter.close();
            System.out.println("Saved score to " + list);
            System.out.println("----------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + list);
            System.out.println("----------------------------------");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads AuthorList from file
    private void loadScore(String list) {
        try {
            score = jsonScoreReader.readScore();
            System.out.println("Loaded score from " + list);
            System.out.println("----------------------------------");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + list);
            System.out.println("----------------------------------");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }

    // EFFECTS: prints all the events on the console after the player quits the game
    public void printlog(EventLog el) {
        for (model.Event next : el) {
            System.out.println(next);
        }
    }

    // ***************************************************************************/
}