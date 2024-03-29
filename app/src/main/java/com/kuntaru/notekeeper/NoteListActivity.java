package com.kuntaru.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    private ArrayAdapter<NoteInfo> mAdapterNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });
        initializeDisplayContent();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapterNote.notifyDataSetChanged();
    }

    private void   initializeDisplayContent(){

   final ListView listNotes = findViewById(R.id.list_note);
    List<NoteInfo> notes = DataManager.getInstance().getNotes();

    mAdapterNote = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, notes);
    listNotes.setAdapter(mAdapterNote);

    listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
//            /NoteInfo note = (NoteInfo) listNotes.getItemAtPosition(position);
            intent.putExtra(NoteActivity.NOTE_POSITION, position);
            startActivity(intent);
        }
    });
}
}
