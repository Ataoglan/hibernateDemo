package controller;


import entity.Note;
import exception.ResourceNotFoundException;
import repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api") /*The @GetMapping("/notes") annotation is a short form of @RequestMapping(value="/notes", method=RequestMethod.GET).*/
public class NoteController {

    @Autowired
    NoteRepository noteRep;
    //Get all notes

    @GetMapping("/notes")
    public List<Note> getAllNotes(){

        return noteRep.findAll();

    }

    //Create note
    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note){
        /*The @RequestBody annotation is used to bind the request body with a method parameter.
          The @Valid annotation makes sure that the request body is valid. Remember, we had marked Note’s title and content with @NotBlank annotation in the Note model?
          If the request body doesn’t have a title or a content, then spring will return a 400 BadRequest error to the client.*/

        return noteRep.save(note);

    }

    //Get a single note
    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") Long noteId){
        return noteRep.findById(noteId)
                .orElseThrow(()-> new ResourceNotFoundException("Note", "id", noteId)); /*The @PathVariable annotation, as the name suggests, is used to bind a path variable with a method parameter.*/
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") Long noteId,
                           @Valid @RequestBody Note noteDetails){

        Note note = noteRep.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        Note updateNote = noteRep.save(note);

        return updateNote;
    }

    //Delete a note
    @DeleteMapping("/notes/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId){

        Note note = noteRep.findById(noteId)
                .orElseThrow(()-> new ResourceNotFoundException("Note", "id", noteId));

        noteRep.delete(note);

        return ResponseEntity.ok().build();
    }
}
