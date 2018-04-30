package com.example.eduardogonzalez.blackjack;

import android.content.Intent;
import android.net.wifi.p2p.WifiP2pManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.*;
import org.w3c.dom.Text;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.example.lib.*;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private int numcpus;
    private Player person;
    private Player left;
    private Player right;
    private Player top;
    private TextView playername;
    ImageView player1;
    ImageView player2;
    ImageView left1;
    ImageView left2;
    ImageView top1;
    ImageView top2;
    ImageView right1;
    ImageView right2;
    TextView toptxt;
    TextView righttxt;
    TextView lefttxt;
    Button start;
    TextView sums;
    ArrayList<Player> winners;
    Boolean gameStarted = false;
    Player startPlayer;
    TextView winview;
    Button draw;
    Button standBtn;
    boolean difficult = false;
    boolean showCards = false;
    final String BACK_OF_CARD_URL = "https://i.pinimg.com/236x/36/c0/58/36c058d97b7ddbbc6b8510cdd43352dd.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Bundle extras = getIntent().getExtras();

        playername = findViewById(R.id.playerTxt);
        playername.setText(extras.getString("name"));
        numcpus = extras.getInt("cpu");
        difficult = extras.getBoolean("difficulty");
        showCards = extras.getBoolean("showCards");

        draw = findViewById(R.id.drawBtn);
        standBtn = findViewById(R.id.standBtn);
        final ImageView playerCard1 = findViewById(R.id.playerCard1);
        final ImageView playerCard2 = findViewById(R.id.playerCard2);
        top1 = findViewById(R.id.topCard1);
        top2 = findViewById(R.id.topCard2);
        left1 = findViewById(R.id.leftCard1);
        left2 = findViewById(R.id.leftCard2);
        right1 = findViewById(R.id.rightCard1);
        right2 = findViewById(R.id.rightCard2);
        final Button reset = findViewById(R.id.resetBtn);
        final Button menu = findViewById(R.id.surrenderBtn);
        final Button start = findViewById(R.id.startBtn);
        final TextView sums = findViewById(R.id.sumsTxt);
        winview = findViewById(R.id.winners);

        shuffleAPICall();


        playerCard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("Cards", person.getUrls());
                startActivity(i);
            }
        });

        playerCard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Main2Activity.class);
                i.putExtra("Cards", person.getUrls());
                startActivity(i);
            }
        });

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("Cards", top.getUrls());
                    startActivity(i);
                }
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    Intent i = new Intent(MainActivity.this, Main2Activity.class);
                    i.putExtra("Cards", top.getUrls());
                    startActivity(i);
                }
            }
        });


        left1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    if (left != null) {
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        i.putExtra("Cards", left.getUrls());
                        startActivity(i);
                    }
                }
            }
        });


        left2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    if (left != null) {
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        i.putExtra("Cards", left.getUrls());
                        startActivity(i);
                    }
                }
            }
        });



        right1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    if (right != null) {
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        i.putExtra("Cards", right.getUrls());
                        startActivity(i);
                    }
                }
            }
        });


        right2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showCards || gameEnded()) {
                    if (right != null) {
                        Intent i = new Intent(MainActivity.this, Main2Activity.class);
                        i.putExtra("Cards", right.getUrls());
                        startActivity(i);
                    }
                }
            }
        });

        standBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.getCurrentPlayer().setCanPlay(false);
                game.nextPlayer();
                while(!game.getCurrentPlayer().equals(startPlayer)) {
                    game.getCurrentPlayer().setCanPlay(false);
                    if (game.getCurrentPlayer() instanceof Computer) {
                        if (gameStarted && ((Computer) game.getCurrentPlayer()).shouldDraw(game.getDeck())) {
                            drawAPICall(game.getCurrentPlayer());
                        }
                        game.nextPlayer();
                    } else {
                        if (game.getCurrentPlayer().shouldDraw()) {
                            drawAPICall(game.getCurrentPlayer());
                        }
                        game.nextPlayer();
                    }
                }
            }
        });


        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawAPICall(game.getCurrentPlayer());
            }
        });


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                i.putExtra("cpu", numcpus);
                i.putExtra("name", extras.getString("name"));

                //WE NEED SOMETHING RIGHT HERE TO STOP THE GLITCH
                /*i.putExtra("difficulty", difficult.isChecked());
                i.putExtra("showCards", ShowCards.isChecked());*/
                startActivity(i);
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(i);

            }
        });


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);
                draw.setVisibility(View.VISIBLE);
                standBtn.setVisibility(View.VISIBLE);
                gameStarted = true;
                while (!game.getCurrentPlayer().equals(person)) {
                    game.getCurrentPlayer().setCanPlay(false);
                    if (game.getCurrentPlayer() instanceof Computer) {
                        if (gameStarted && ((Computer) game.getCurrentPlayer()).shouldDraw(game.getDeck())) {
                            drawAPICall(game.getCurrentPlayer());
                        }
                        game.nextPlayer();
                    } else {
                        if (game.getCurrentPlayer().shouldDraw()) {
                            drawAPICall(game.getCurrentPlayer());
                        }
                        game.nextPlayer();
                    }
                }
            }
        });

    }

    void drawAPICall(final Player player) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://deckofcardsapi.com/api/deck/"+ game.getDeck().getDeckID() + "/draw/?count=1",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            //gets card and gives it to player
                            getCard(response, player);
                            //draws the card
                            drawImage(player);
                            //checks to see if the dealer is current player.
                            if (player instanceof Dealer) {
                                // if the game has started and if the dealer should draw
                                if (gameStarted && player.shouldDraw()) {
                                    player.setCanPlay(false);
                                    drawAPICall(player);
                                }
                                //if the Computer is playing.
                            } else if (player instanceof Computer) {
                                if (gameStarted && ((Computer) player).shouldDraw(game.getDeck())) {
                                    player.setCanPlay(false);
                                    drawAPICall(player);
                                }
                                //if the user is playing.
                            } else {
                                if (gameStarted) {
                                    player.setCanPlay(false);
                                    //IF the user goes over 21, automatically stands.
                                    if (player.getSums().get(0) > 21) {
                                        draw.setVisibility(View.INVISIBLE);
                                        standBtn.setVisibility(View.INVISIBLE);
                                        standBtn.callOnClick();
                                    }
                                }
                            }
                            //Once current player has drawn as much as possible, they can no longer
                            //play.
                            if (gameStarted) {
                                player.setCanPlay(false);
                            }
                            getWinner();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void shuffleAPICall() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            getDeckId(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                }
            });
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
        }
    }

    void getDeckId(JSONObject jsonObject) {
        try {
            String deckId = jsonObject.getString("deck_id");
            int cardsLeft = jsonObject.getInt("remaining");
            Deck deck = new Deck(deckId);
            deck.setCardsRemaining(cardsLeft);
            ArrayList<Player> players = getPlayers(numcpus);
            game = new Game(players, deck);
            setOrder(game.getPlayers().size());

        } catch (JSONException ignored) {
        }
    }

    void getCard(JSONObject json, Player player) {
        String code = null;
        String imageUrl = null;
        try {
            JSONArray array = json.getJSONArray("cards");
            for (int i = 0; i < array.length(); i++ ) {
                if (code == null) {
                    code = array.getJSONObject(i).getString("code");
                }
                if (imageUrl == null) {
                    imageUrl = array.getJSONObject(i).getString("image");
                }
                if (imageUrl != null && code != null) {
                    int val = Card.values.get(code.substring(0,1));
                    Card card = new Card(imageUrl, val);
                    game.getDeck().removeCard(code.substring(0,1));
                    game.getDeck().setCardsRemaining(game.getDeck().getCardsRemaining() - 1);
                    player.addCard(card);
                }
            }
        } catch (JSONException ignored) {
        }
    }

    /**
     * Draws image for the player depending on location.
     * @param player - the player playing.
     */
    void drawImage(Player player) {
        switch (player.getLocation()) {
            case TOP:
                top1 = findViewById(R.id.topCard1);
                top2 = findViewById(R.id.topCard2);
                if (showCards) {
                    loadFromUrl(player.getCard(player.getCardLength() - 1).getUrl(), top1, player.getLocation());
                    if (player.getCardLength() >= 2) {
                        loadFromUrl(player.getCard(player.getCardLength() - 2).getUrl(), top2, player.getLocation());
                    }
                } else {
                    loadFromUrl(BACK_OF_CARD_URL, top1, player.getLocation());
                    loadFromUrl(BACK_OF_CARD_URL, top2, player.getLocation());
                }
                break;
            case LEFT:
                left1 = findViewById(R.id.leftCard1);
                left2 = findViewById(R.id.leftCard2);
                if (showCards) {
                    loadFromUrl(player.getCard(player.getCardLength() - 1).getUrl(), left1, player.getLocation());
                    if (player.getCardLength() >= 2) {
                        loadFromUrl(player.getCard(player.getCardLength() - 2).getUrl(), left2, player.getLocation());
                    }
                } else {
                    loadFromUrl(BACK_OF_CARD_URL, left1, player.getLocation());
                    loadFromUrl(BACK_OF_CARD_URL, left2, player.getLocation());
                }
                break;
            case BOTTOM:
                ImageView cardPlayer1 = findViewById(R.id.playerCard1);
                ImageView cardPlayer2 = findViewById(R.id.playerCard2);
                sums = findViewById(R.id.sumsTxt);
                sums.setText(player.getSumsAsString());
                loadFromUrl(player.getCard(player.getCardLength() - 1).getUrl(), cardPlayer1, player.getLocation());
                if (player.getCardLength() >= 2) {
                    loadFromUrl(player.getCard(player.getCardLength() - 2).getUrl(), cardPlayer2, player.getLocation());
                }

                break;
            case RIGHT:
                right1 = findViewById(R.id.rightCard1);
                right2 = findViewById(R.id.rightCard2);
                if (showCards) {
                    loadFromUrl(player.getCard(player.getCardLength() - 1).getUrl(), right1, player.getLocation());
                    if (player.getCardLength() >= 2) {
                        loadFromUrl(player.getCard(player.getCardLength() - 2).getUrl(), right2, player.getLocation());
                    }
                } else {
                    loadFromUrl(BACK_OF_CARD_URL, right1, player.getLocation());
                    loadFromUrl(BACK_OF_CARD_URL, right2, player.getLocation());
                }
                break;
                default:

        }
    }

    /**
     * loads the image
     * @param url - the image URl
     * @param image - the ImageView.
     * @param location - the location of the image. Image gets rotated based on position.
     */
    public void loadFromUrl(final String url, ImageView image, Location location) {

        switch (location) {
            case TOP:
                Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).rotate(180).into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });
                break;
            case RIGHT:
                Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).rotate(270).into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });
                break;
            case LEFT:
                Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).rotate(90).into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });
                break;
            case BOTTOM:
                Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher).into(image, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });
                break;
        }
    }

    /**
     *
     * @param i - number of computers in the game.
     * @return - the ArrayList of all the players.
     */
    public ArrayList<Player> getPlayers(final int i) {
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(new Player());
        players.add(new Dealer());
        switch(i) {
            case 1:
                return players;

            case 2:
                Computer comp1 = new Computer();
                comp1.setDifficult(difficult);
                players.add(comp1);
                return players;

            case 3:
                Computer comp2 = new Computer();
                Computer comp3 = new Computer();
                comp2.setDifficult(difficult);
                comp3.setDifficult(difficult);
                players.add(comp2);
                players.add(comp3);
                return players;

                default:
                    return null;
        }
    }

    /**
     * This rotates who the dealer is, excluding the user.
     * @param i - Number of players.
     */
    public void setOrder(int i) {
        game.reorderArray();
        startPlayer = game.getCurrentPlayer();
        person = game.getPlayers().get(findPlayer());
        person.setLocation(Location.BOTTOM);
        person.setName(String.valueOf(playername.getText()));
        toptxt = findViewById(R.id.topTxt);
        righttxt = findViewById(R.id.rightTxt);
        lefttxt = findViewById(R.id.leftTxt);
        switch (i) {
            case 2:
                top = game.getPlayers().get(0);
                top.setLocation(Location.TOP);
                toptxt.setText(top.getName());
                toptxt.setVisibility(View.VISIBLE);

                drawAPICall(top);
                drawAPICall(top);
                drawAPICall(person);
                drawAPICall(person);
                break;
            case 3:
                if (game.getPlayers().size() - 1 - findPlayer() == 0) {
                    left = game.getPlayers().get(0);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(1);
                    top.setLocation(Location.TOP);

                }
                if (game.getPlayers().size() - 1 - findPlayer() == 1) {
                    left = game.getPlayers().get(2);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(0);
                    top.setLocation(Location.TOP);

                }
                if (game.getPlayers().size() - 1 - findPlayer() == 2) {
                    left = game.getPlayers().get(1);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(2);
                    top.setLocation(Location.TOP);

                }
                toptxt.setText(top.getName());
                toptxt.setVisibility(View.VISIBLE);

                lefttxt.setText(left.getName());
                lefttxt.setVisibility(View.VISIBLE);

                drawAPICall(top);
                drawAPICall(top);
                drawAPICall(person);
                drawAPICall(person);
                drawAPICall(left);
                drawAPICall(left);
                break;
            //checks player position and puts them in the order of the ArrayList.
            case 4:
                if (game.getPlayers().size() - 1 - findPlayer() == 0) {
                    left = game.getPlayers().get(0);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(1);
                    top.setLocation(Location.TOP);
                    right = game.getPlayers().get(2);
                    right.setLocation(Location.RIGHT);
                }
                if (game.getPlayers().size() - 1 - findPlayer() == 1) {
                    left = game.getPlayers().get(3);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(0);
                    top.setLocation(Location.TOP);
                    right = game.getPlayers().get(1);
                    right.setLocation(Location.RIGHT);
                }
                if (game.getPlayers().size() - 1 - findPlayer() == 2) {
                    left = game.getPlayers().get(2);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(3);
                    top.setLocation(Location.TOP);
                    right = game.getPlayers().get(0);
                    right.setLocation(Location.RIGHT);
                }
                if (game.getPlayers().size() - 1 - findPlayer() == 3) {
                    left = game.getPlayers().get(1);
                    left.setLocation(Location.LEFT);
                    top = game.getPlayers().get(2);
                    top.setLocation(Location.TOP);
                    right = game.getPlayers().get(3);
                    right.setLocation(Location.RIGHT);
                }
                toptxt.setText(top.getName());
                toptxt.setVisibility(View.VISIBLE);

                lefttxt.setText(left.getName());
                lefttxt.setVisibility(View.VISIBLE);

                righttxt.setText(right.getName());
                righttxt.setVisibility(View.VISIBLE);

                drawAPICall(top);
                drawAPICall(top);
                drawAPICall(right);
                drawAPICall(right);
                drawAPICall(person);
                drawAPICall(person);
                drawAPICall(left);
                drawAPICall(left);

                break;

                default:
        }
    }

    public int findPlayer() {
        for (int i = 0; i < game.getPlayers().size(); i++) {
            if (!(game.getPlayers().get(i) instanceof Dealer) && !(game.getPlayers().get(i) instanceof Computer)) {
                return i;
            }
        }
        return 0;
    }

    /**
     * This calculates who wins and displays the winner(s).
     */
    public void getWinner() {
        winners = new ArrayList<>();
        int biggestUnder21 = 0;
        int sum;
        if (gameEnded()) {
            for (Player player : game.getPlayers()) {
                for (int j = 0; j < 2; j++) {
                    sum = player.getSums().get(j);
                    if (sum > biggestUnder21 && sum <= 21) {
                        biggestUnder21 = sum;
                    }
                }
            }
            for (Player player : game.getPlayers()) {
                for (int j = 0; j < 2; j++) {
                    sum = player.getSums().get(j);
                    if (sum == biggestUnder21 && !winners.contains(player)) {
                        winners.add(player);
                    }
                }
            }
            String win;
            if (winners.size() == 0) {
                win = "The Dealer Wins";
            } else if (winners.size() == 1) {
                win = "The Winner Is: \n";
            } else {
                win = "The Winners Are: \n";
            }
            for (Player player : winners) {
                win += player.getName() + "\n";
            }
            winview.setText(win);
            draw.setVisibility(View.INVISIBLE);
            standBtn.setVisibility(View.INVISIBLE);
            winview.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Checks if the game is over.
     * @return - Returns true if the game is over. False otherwise.
     */
    public boolean gameEnded() {
        for (Player player: game.getPlayers()) {
            if (player.canPlay()) {
                return false;
            }
        }
        return true;
    }
}
