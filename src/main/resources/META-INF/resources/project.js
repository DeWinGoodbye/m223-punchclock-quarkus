const URL = 'http://localhost:8080';
let projects = [];
$(document).ready(function() {
    fetchProjects();
});

function fetchProjects() {
    fetch(`${URL}/projects`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            projects = result;
            loadProjects();
        });
    });
    loadProjects();
}

document.getElementById("addProjectForm").addEventListener("submit", addProject);
function addProject(e){
    const data = {};
    const formData = new FormData(e.target);
    data["title"] = formData.get("title");
    e.preventDefault();
    fetch(`${URL}/projects`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((result) => {
        result.json().then((project) => {
            fetchProjects();
        });
    });
}

function deleteProject(id) {
    fetch(`${URL}/projects/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function() {
        fetchProjects();
    });
}


function loadProjects() {
    let projectDisplay = document.getElementById("projectDisplay");
    projectDisplay.innerHTML = "";
    projects.forEach(function(project) {
        const row = document.createElement('tr');
        const id = document.createElement('td');
        id.innerText = project.id;
        const title = document.createElement('td');
        title.innerText = project.title;
        const deleteBtn = document.createElement('button');
        deleteBtn.innerText = "Delete";
        deleteBtn.onclick = function() {
            deleteProject(project.id);
        };
        row.appendChild(id);
        row.appendChild(title);
        row.appendChild(deleteBtn);
        projectDisplay.append(row);
    });
}