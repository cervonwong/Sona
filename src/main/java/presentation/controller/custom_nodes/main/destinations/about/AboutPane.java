/*
 * Sona is an educational SRS application written in JavaFX.
 * Copyright (C) 2020 Cervon Wong
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package main.java.presentation.controller.custom_nodes.main.destinations.about;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import main.java.i18n.ResourceBundles;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ResourceBundle;

import java.awt.Desktop;

public class AboutPane extends AnchorPane {

    // INSTANCE VARIABLES

    private final ResourceBundle messages;


    // FXML

    @FXML
    private Label summaryLabel, authorLabel;

    @FXML
    private Button githubButton;

    @FXML
    private Label versionLabel;


    // CONSTRUCTOR

    public AboutPane() {
        messages = initializeMessages();

        initializeFxml();
        initializeText();
        initializeGithubButton();
    }


    // INITIALIZERS

    private ResourceBundle initializeMessages() {
        return ResourceBundle.getBundle(ResourceBundles.ABOUT_PANE.getBundleName());
    }

    private void initializeFxml() {
        final String RESOURCE_PATH =
                "/view/fxml/custom_nodes/main/destinations/about/about_pane.fxml";

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(RESOURCE_PATH));

        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initializeText() {
        summaryLabel.setText(messages.getString("summary"));
        authorLabel.setText(messages.getString("author"));
        githubButton.setText(messages.getString("github"));
    }

    private void initializeGithubButton() {
        githubButton.setOnAction(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://github.com/cervonwong/Sona"));
            } catch (IOException | URISyntaxException ex) {
                ex.printStackTrace();
            }
        });
    }
}