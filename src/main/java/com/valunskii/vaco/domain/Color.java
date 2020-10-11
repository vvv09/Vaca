package com.valunskii.vaco.domain;

public enum Color {
    RED_LIGHTEN("red lighten", "#ef5350"),
    PINK_LIGHTEN("pink lighten", "#ec407a"),
    PURPLE_LIGHTEN("purple lighten", "#ab47bc"),
    RED_DARKEN("red darken", "#e53935"),
    PINK_DARKEN("pink darken", "#d81b60"),
    PURPLE_DARKEN("purple darken", "#8e24aa"),
    DEEP_PURPLE_LIGHTEN("deep-purple lighten", "#7e57c2"),
    INDIGO_LIGHTEN("indigo lighten", "#5c6bc0"),
    BLUE_LIGHTEN("blue lighten", "#42a5f5"),
    DEEP_PURPLE_DARKEN("deep-purple darken", "#5e35b1"),
    INDIGO_DARKEN("indigo darken", "#3949ab"),
    LIGHT_BLUE("light-blue", "#03a9f4"),
    CYAN("cyan", "#00bcd4"),
    TEAL("teal", "#009688"),
    LIGHT_BLUE_DARKEN("light-blue darken", "#01579b"),
    CYAN_DARKEN("cyan darken", "#006064"),
    TEAL_DARKEN("teal darken", "#004d40"),
    GREEN("green", "#4caf50"),
    LIGHT_GREEN("light-green", "#8bc34a"),
    LIME("lime", "#cddc39"),
    GREEN_ACCENT("green accent", "#00e676"),
    LIGHT_GREEN_ACCENT("light-green accent", "#76ff03"),
    LIME_ACCENT("lime accent", "#c6ff00"),
    YELLOW("yellow", "#ffeb3b"),
    AMBER("amber", "#ffc107"),
    ORANGE("orange", "#ff9800"),
    YELLOW_DARKEN("yellow darken", "#f57f17"),
    AMBER_DARKEN("amber darken", "#ff6f00"),
    ORANGE_DARKEN("orange darken", "#e65100"),
    DEEP_ORANGE("deep-orange", "#ff5722"),
    BROWN("brown", "#795548"),
    GREY("grey", "#9e9e9e"),
    DEEP_ORANGE_DARKEN("deep-orange darken", "#bf360c"),
    GREY_DARKEN("grey darken", "#212121"),
    BLACK("black", "#000000");

    private String name;
    private String hash;

    Color(String name, String hash){
        this.name = name;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getHash() {
        return hash;
    }
}
