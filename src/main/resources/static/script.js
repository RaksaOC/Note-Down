let selectedId;

// add a note title box
const noteTextBox = document.querySelector("#note-box");
const noteTitleBox = document.querySelector("#note-title")
const noteList = document.querySelector(".note-select");
const saveButton = document.querySelector("#save-button");
const deleteButton = document.querySelector("#delete-button");
const addButton = document.querySelector("#add-note");

// authentication buttons

const logIn = document.querySelector("#log-in")
const logOut = document.querySelector("#log-out")
const signUp = document.querySelector("#sign-up")

// modal

const logInModal = document.querySelector("#login-modal");
const signUpModal = document.querySelector("#signup-modal");

async function isLoggedIn() {
    try {
        const res = await fetch('/notes/session');

        return await res.json(); // returns true or false from backend method
    } catch (err) {
        console.error('Error checking session:', err);
    }
}

async function configAuthButton() {
    if (await isLoggedIn()) {
        logIn.style.display = "none";
        signUp.style.display = "none";
        logOut.style.display = "flex";
    } else {
        logIn.style.display = "flex";
        signUp.style.display = "flex";
    }
}

configAuthButton();

function disableInputs() {
    noteTextBox.value = "";
    noteTitleBox.value = "";
    noteTitleBox.setAttribute("disabled", "disabled");
    noteTextBox.setAttribute("disabled", "disabled");
    saveButton.setAttribute("disabled", "disabled");
    deleteButton.setAttribute("disabled", "disabled");
    noteTitleBox.style.opacity = "0.5";
    noteTextBox.style.opacity = "0.5";
    deleteButton.style.opacity = "0.5";
    saveButton.style.opacity = "0.5";
}

function enableInputs() {
    noteTitleBox.removeAttribute("disabled");
    noteTextBox.removeAttribute("disabled");
    saveButton.removeAttribute("disabled");
    deleteButton.removeAttribute("disabled");
}


async function getAllNotes() {
    const response = await fetch("http://localhost:8080/notes/allNotes",
        {
            method: "GET",
            headers: {
                'Content-Type': "application/json"
            }
        });
    return await response.json();
}

async function saveNote() {
    const body = JSON.stringify({
        "title": "New Note",
        "content": ""
    });
    const response = await fetch(`http://localhost:8080/notes`, {
        method: "POST",
        headers: {
            'Content-Type': "application/json"
        },
        body: body
    });
}

async function updateNote() {
    const response = await fetch(`http://localhost:8080/notes/${selectedId}`, {
        method: "PUT",
        headers: {
            'Content-Type': "application/json"
        },
        body: JSON.stringify({
            "title": noteTitleBox.value,
            "content": noteTextBox.value
        })
    });
}

async function deleteNote() {
    const response = await fetch(`http://localhost:8080/notes/${selectedId}`, {
        method: "DELETE",
        headers: {
            'Content-Type': "application/json"
        },
    });
}

deleteButton.addEventListener("click", async () => {
    await deleteNote();
    await reload();
    selectedId = null;
    disableInputs();
})

saveButton.addEventListener("click", async () => {
    // this actually updates the note
    await updateNote();
    await reload();
})

// populate notes list

async function allNotes() {
    const notes = await getAllNotes(); // Get notes
    notes.forEach((note, index) => {
        const noteItem = document.createElement('div'); // Create a div for each note
        noteItem.setAttribute("class", "note");
        noteItem.textContent = note.title; // Or whatever the note's text field is
        noteItem.addEventListener('click', () => {
            highlightNote(notes, noteItem)
            populateInputs(note);
        })
        noteList.appendChild(noteItem); // Append each note to the list
    });
}

allNotes();

addButton.addEventListener("click", async () => {
    if (await isLoggedIn()) {
        await saveNote();
        await reload();
    }
    else{
        logInModal.style.display = "flex";
    }
});

function highlightNote(notes, noteElement) {
    // Remove highlight class from all children
    Array.from(noteList.children).forEach(child => {
        child.classList.remove("highlight");
    });

    // Add highlight to the clicked one
    noteElement.classList.add("highlight");
}

async function reload() {
    noteList.innerHTML = "";
    selectedId = null;
    await allNotes();
}

function populateInputs(note) {
    enableInputs();
    selectedId = note.id;
    noteTextBox.value = note.content;
    noteTitleBox.value = note.title;
}

logIn.addEventListener("click", () => {
    logInModal.style.display = "flex";
})

signUp.addEventListener("click", () => {
    signUpModal.style.display = "flex";
})