import javax.swing.JFrame;

public class SimpleNotePad extends JFrame {

        private MenuBar menuBar;

        public SimpleNotePad() {
            menuBar =  new MenuBar();;
        }

        public static void main(String[] args) {
            SimpleNotePad notePad = new SimpleNotePad();
        }

}

