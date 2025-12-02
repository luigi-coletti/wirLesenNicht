package ch.prog1.wirlesennicht.views.media;

import ch.prog1.wirlesennicht.data.Media;
import ch.prog1.wirlesennicht.services.Controller;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Optional;

@Route("media/:mediaId")
@PageTitle("Media Details")
public class MediaDetailView extends VerticalLayout implements BeforeEnterObserver {

    private final Controller controller;
    private Media media;

    public MediaDetailView(Controller controller) {
        this.controller = controller;
        setPadding(true);
        setSpacing(true);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Optional<String> mediaId = event.getRouteParameters().get("mediaId");

        if (mediaId.isEmpty()) {
            event.forwardTo(""); // zurück zur Hauptseite
            return;
        }

        Media media = controller.getMediaById(mediaId.get());
        if (media == null) {
            add(new Text("Medium nicht gefunden"));
            return;
        }

        this.media = media;

        buildLayout();
    }

    private void buildLayout() {
        removeAll();

        // --- Hauptkarte ---
        VerticalLayout card = new VerticalLayout();
        card.setWidthFull();
        card.setPadding(true);
        card.setSpacing(true);
        card.getStyle()
                .set("border-radius", "12px")
                .set("border", "1px solid var(--lumo-contrast-20pct)")
                .set("padding", "20px")
                .set("max-width", "600px")
                .set("margin", "auto")
                .set("box-shadow", "var(--lumo-box-shadow-s)")
                .set("background", "var(--lumo-base-color)");

        // Titel
        H2 title = new H2(media.getTitle());
        title.getStyle().set("margin-top", "0");

        // Beschreibung
        Paragraph desc = new Paragraph(media.getDescription());
        desc.getStyle().set("white-space", "pre-line");

        // Meta Infos (Ersteller / Datum)
        HorizontalLayout meta = new HorizontalLayout();
        meta.setWidthFull();
        meta.setJustifyContentMode(FlexComponent.JustifyContentMode.BETWEEN);

        Span creator = new Span("📘 Ersteller: " + media.getCreator());
        Span date;
        if (media.getLentDate() != null) {
             date = new Span("📅 Ausgeliehen bis: " + media.getLentDate());

        } else {
             date = new Span("📅 Ausleihen bis: " + media.getPossibleLentDate());

        }

        meta.add(creator, date);

        // Footer mit Button
        HorizontalLayout footer = new HorizontalLayout();
        footer.setWidthFull();
        footer.setJustifyContentMode(FlexComponent.JustifyContentMode.END);

        Button borrowBtn = new Button(
                media.getLentDate() != null ? "Zurückgeben" : "Ausleihen"
        );

        borrowBtn.getStyle()
                .set("border-radius", "10px")
                .set("padding", "10px 20px")
                .set("font-weight", "600");

        borrowBtn.addClickListener(click -> {
            if (media.getLentDate() != null) {
                openReturnDialog(media);
            } else {
                openBorrowDialog(media);
            }
        });

        footer.add(borrowBtn);

        // Layout zusammensetzen
        card.add(title, desc, meta, footer);
        add(card);
    }

    private void openBorrowDialog(Media media) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Medium ausleihen");

        TextField firstName = new TextField("Vorname");
        TextField lastName = new TextField("Nachname");

        VerticalLayout layout = new VerticalLayout(firstName, lastName);
        dialog.add(layout);

        Button confirm = new Button("Ausleihen", event -> {
            controller.lendMedia(media, firstName.getValue(), lastName.getValue());
            dialog.close();
            UI.getCurrent().getPage().reload();
        });

        Button cancel = new Button("Abbrechen", event -> dialog.close());

        dialog.getFooter().add(cancel, confirm);
        dialog.open();
    }

    private void openReturnDialog(Media media) {
        Dialog dialog = new Dialog();
        dialog.setHeaderTitle("Medium zurückgeben");

        dialog.add(new Span("Möchtest du dieses Medium wirklich zurückgeben?"));

        Button confirm = new Button("Zurückgeben", event -> {
            controller.returnMedia(media);
            dialog.close();
            UI.getCurrent().getPage().reload();
        });

        Button cancel = new Button("Abbrechen", event -> dialog.close());

        dialog.getFooter().add(cancel, confirm);
        dialog.open();
    }


}

