/*
* Requirements mandating inclusion:
* */
package model.GameEvents;


import model.GameRule;



public class OnRoundEndEvent extends GameEvent {

    public OnRoundEndEvent() {
        this.className = "OnRoundEndEvent";
        this.description = "The round is ending.";
    }

    @Override
    public void run(Object... args) {
        for (int i = 0; i < args.length; i++) {
            GameRule rule = (GameRule) args[i];
            rule.run(args);
        }
    }
}
