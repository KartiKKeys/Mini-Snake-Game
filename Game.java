import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Game implements KeyListener {
        private Snake player;
        private Food food;
        private Graphics graphic;
        private JFrame window;
        public static final int width = 30;
        public static final int  height = 30;
        public static final int  dimension = 20;

    public Game(){
        window = new JFrame();

        player =  new Snake();
        food =  new Food(player);
        graphic = new Graphics(this);

        window.add(graphic);

        window.setTitle("Snake");
        window.setSize(width * dimension +2, height * dimension);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void start(){
        graphic.state = "RUNNING";
    }

    public void update(){
         if(graphic.state == "RUNNING"){
             if(checkEatFood()){
                 player.grow();
                 food.randomSpawn(player);
             }
             else if(checkWallCollosion() || checkSelfCollosion()){
                 graphic.state = "END";
             }
             else{
                 player.move();
             }
         }
    }

    private boolean checkWallCollosion(){
        if(player.getX() < 0 || player.getX() >= width * dimension || player.getY() < 0 ||
                player.getY() >= height * dimension ){
            return true;
        }
        return false ;
    }

     private boolean checkEatFood(){
         if(player.getX() == food.getX() * dimension && player.getY() == food.getY() * dimension){
             return true;
         }
         return false;
     }

     private boolean checkSelfCollosion(){
        for(int i=1; i < player.getBody().size(); i++)
            if ((player.getX() == player.getBody().get(i).x) && (player.getY() == player.getBody().get(i).y)) {
                return true;
            }
        return false;
     }
    public void setGraphic(Graphics graphic) {
        this.graphic = graphic;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }
    public Snake getPlayer() {
        return player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //does nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(graphic.state == "RUNNING") {

            if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP) {
                player.up();
            } else if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN) {
                player.down();
            } else if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) {
                player.left();
            } else {
                player.right();
            }

        }
        else{
            this.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    public void setWindow(JFrame window) {
        this.window = window;
    }
    public JFrame getWindow() {
        return window;
    }
}
