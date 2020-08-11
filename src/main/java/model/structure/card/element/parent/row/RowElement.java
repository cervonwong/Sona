package main.java.model.structure.card.element.parent.row;

import main.java.model.structure.card.element.AbstractElement;
import main.java.model.structure.card.element.control.ControlElement;

import java.util.ArrayList;

public final class RowElement extends AbstractElement {

    // INSTANCE VARIABLES

    private final ArrayList<ControlElement> children;

    private final HorizontalAlignment horizontalAlignment;


    // DEFAULT VALUES

    private static final String DEFAULT_NAME = "New Group Element";

    private static final ArrayList<ControlElement> DEFAULT_CHILDREN = new ArrayList<>();

    private static final HorizontalAlignment DEFAULT_HORIZONTAL_ALIGNMENT =
            HorizontalAlignment.CENTER;


    // CONSTRUCTOR

    @SuppressWarnings("unchecked")
    private RowElement(String name,
                       ArrayList<ControlElement> children,
                       HorizontalAlignment horizontalAlignment) {
        super(name); // Parent class does checking, no checking needed for name.

        checkArguments(children, horizontalAlignment);

        this.children = (ArrayList<ControlElement>) children.clone(); // Defensive copy.
        this.horizontalAlignment = horizontalAlignment;
    }


    // CHECK METHODS

    private void checkArguments(ArrayList<ControlElement> children,
                                HorizontalAlignment horizontalAlignment) {
        if (children == null)
            throw new IllegalArgumentException("Illegal children (cannot be null)");

        for (ControlElement child : children) {
            if (child == null)
                throw new IllegalArgumentException("Illegal children (a child is null)");
        }

        if (horizontalAlignment == null)
            throw new IllegalArgumentException("Illegal horizontalAlignment (cannot be null)");
    }


    // ACCESSORS (Instance Variables)

    @SuppressWarnings("unchecked")
    public ArrayList<ControlElement> getChildren() {
        return (ArrayList<ControlElement>) children.clone();
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }


    // ACCESSORS (Default Values)

    public static String getDefaultName() {
        return DEFAULT_NAME;
    }

    @SuppressWarnings("unchecked")
    public static ArrayList<ControlElement> getDefaultChildren() {
        return (ArrayList<ControlElement>) DEFAULT_CHILDREN.clone();
    }

    public static HorizontalAlignment getDefaultHorizontalAlignment() {
        return DEFAULT_HORIZONTAL_ALIGNMENT;
    }


    // COPY METHODS

    @Override
    public RowElement withName(String newName) {
        if (newName == null)
            throw new IllegalArgumentException("Illegal newName (cannot be null)");

        if (newName.equals(getName())) return this;

        final var BUILDER = new RowElementBuilder();
        return BUILDER.name(newName)
                      .children(children)
                      .horizontalAlignment(horizontalAlignment)
                      .build();
    }

    public RowElement replaceChildren(ArrayList<ControlElement> newChildren) {
        if (newChildren == null)
            throw new IllegalArgumentException("Illegal newChildren (cannot be null)");

        if (newChildren.equals(children)) return this;

        final var BUILDER = new RowElementBuilder();
        return BUILDER.name(getName())
                      .children(newChildren)
                      .horizontalAlignment(horizontalAlignment)
                      .build();
    }


    @SuppressWarnings("unchecked")
    public RowElement insertChildren(ControlElement element, int index) {
        if (element == null)
            throw new IllegalArgumentException("Illegal element (cannot be null)");

        if (!(index >= 0 && index <= children.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    children.size(),
                    index
            ));

        ArrayList<ControlElement> newChildren = (ArrayList<ControlElement>) children.clone();
        newChildren.add(index, element);

        final var BUILDER = new RowElementBuilder();
        return BUILDER.name(getName())
                      .children(newChildren)
                      .horizontalAlignment(horizontalAlignment)
                      .build();
    }

    @SuppressWarnings("unchecked")
    public RowElement deleteChildren(int index) {
        if (!(index >= 0 && index < children.size()))
            throw new IllegalArgumentException(String.format(
                    "Illegal index (out of bounds 0 to %s inclusive): %s",
                    children.size() - 1,
                    index
            ));

        ArrayList<ControlElement> newChildren = (ArrayList<ControlElement>) children.clone();
        newChildren.remove(index);

        final var BUILDER = new RowElementBuilder();
        return BUILDER.name(getName())
                      .children(newChildren)
                      .horizontalAlignment(horizontalAlignment)
                      .build();
    }

    public RowElement withHorizontalAlignment(HorizontalAlignment newHorizontalAlignment) {
        if (newHorizontalAlignment == null)
            throw new IllegalArgumentException("Illegal newHorizontalAlignment (cannot be null)");

        if (newHorizontalAlignment == horizontalAlignment) return this;

        final var BUILDER = new RowElementBuilder();
        return BUILDER.name(getName())
                .children(children)
                .horizontalAlignment(newHorizontalAlignment)
                .build();
    }


    // BUILDER

    public static final class RowElementBuilder {

        // RowElement VARIABLES

        private String name = DEFAULT_NAME;

        private ArrayList<ControlElement> children = DEFAULT_CHILDREN;

        private HorizontalAlignment horizontalAlignment = DEFAULT_HORIZONTAL_ALIGNMENT;


        // CONSTRUCTOR

        public RowElementBuilder() {}


        // METHODS

        public RowElementBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RowElementBuilder children(ArrayList<ControlElement> children) {
            this.children = children;
            return this;
        }

        public RowElementBuilder horizontalAlignment(HorizontalAlignment horizontalAlignment) {
            this.horizontalAlignment = horizontalAlignment;
            return this;
        }


        // BUILD

        public RowElement build() {
            return new RowElement(name, children, horizontalAlignment);
        }
    }
}