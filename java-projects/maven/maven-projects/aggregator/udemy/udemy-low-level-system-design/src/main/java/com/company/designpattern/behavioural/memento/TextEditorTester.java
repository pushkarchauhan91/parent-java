package com.company.designpattern.behavioural.memento;

public class TextEditorTester {
    static void main() {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker(); //History / StateMgmt

        editor.write("A");
        caretaker.saveState(editor);

        editor.write("B");
        caretaker.saveState(editor);
        //Problem - > Undo the last write!
        editor.write("C");
        caretaker.saveState(editor);

        //CareTaker Undo
        caretaker.undo(editor);

        System.out.println(editor.getContent());
    }
}
