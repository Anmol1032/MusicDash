# Create a class

```java
public class Level extends AbstractLevel {
    public Level() {
        super();
        LEVEL_NAME_IN = "Level Name"; // when not completed
        LEVEL_NAME_OUT = "Level Name when completed";
        LEVEL_DESCRIPTION_IN = "Description";
        LEVEL_DESCRIPTION_OUT = "Description when completed";
        Stars = 1; // stars
        StarsRequirment = 9; // how much stars user should have to play level
        id = 1007;
    }

    @Override
    public void implement(GameView gameView) {
    }
}
```

# Use implement Method

```java
public class Level extends AbstractLevel {
    public Level() {
        // ...... //
    }

    @Override
    public void implement(GameView gameView) {
        gameView.addItem(new BgColor(0x88ffdd, 2000, 1000)); // switch bgColor after 2sec with 1sec of easing
        gameView.addItem(new Circle(1500, 500, 999, 0.5f, 0.5f, 0.25f, Color.WHITE, new GameSoundPlayer.Sound(f[9], 0.5f, 300))); // add Circle.
        gameView.addItem(new End(3000)); // Win game at 3000ms.
    }
}
```

# Add level

In class Levels

```java
public class Levels {
public static final AbstractLevel[] levels = new AbstractLevel[]{
new Level() // add level where you want        
};
}
```

# More help

For more help see otter levels or Items.
