package com.animation;

import javafx.scene.control.TextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class animate {
	
	public static void fade_in(Pane pane) {
    	FadeTransition fd1=new FadeTransition(Duration.seconds(0.6), pane);
    	fd1.setFromValue(0);
    	fd1.setToValue(1);
    	fd1.play();
    }
	
	public static void fade_in(TextField textField) {
    	FadeTransition fd1 = new FadeTransition(Duration.seconds(0.6), textField);
    	fd1.setFromValue(0);
    	fd1.setToValue(1);
    	fd1.play();
    }
    
	public static void fade_in(Label lbl) {
    	FadeTransition fd1=new FadeTransition(Duration.seconds(0.6), lbl);
    	fd1.setFromValue(0);
    	fd1.setToValue(1);
    	fd1.play();
	}
    
	public static void fade_out(Pane panel) {
    	FadeTransition fd1=new FadeTransition(Duration.seconds(0.6), panel);
    	fd1.setFromValue(1);
    	fd1.setToValue(0);
    	fd1.play();
    }
	
	public static void fade_out(TextField textField) {
    	FadeTransition fd1=new FadeTransition(Duration.seconds(0.6), textField);
    	fd1.setFromValue(1);
    	fd1.setToValue(0);
    	fd1.play();
    }
    
	public static void fade_out(Label lbl) {
    	FadeTransition fd1=new FadeTransition(Duration.seconds(0.6), lbl);
    	fd1.setFromValue(1);
    	fd1.setToValue(0);
    	fd1.play();
    }
    
	public static void translate_transition(Pane pane, int x) {
    	TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.6), pane);
    	tt1.setByX(x);
    	tt1.play();
    }
	
	public static void translate_transition(Pane pane, double x) {
    	TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.6), pane);
    	tt1.setByX(x);
    	tt1.play();
    }
	
	public static void translate_transition(TabPane pane, double x) {
    	TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.6), pane);
    	tt1.setByX(x);
    	tt1.play();
    }
	
	public static void translate_transition(Label lbl, int x) {
    	TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.6), lbl);
    	tt1.setByX(x);
    	tt1.play();
    }
}
