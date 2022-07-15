const URL = 'http://localhost:8080';
let projects = [];
$(document).ready(function() {
    fetchProjects();
});


function openUpdateProjectForm() {
    document.getElementById("error").innerText = "";
    document.getElementById("createProjectForm").removeEventListener("submit", createProject);
    document.getElementById("createProjectForm").addEventListener("submit", updateProject);
    document.getElementById("formTitle").innerText = "Update project";
    document.getElementById("updateBack").style.display = "block";
}

function closeUpdateProjectForm() {
    document.getElementById("error").innerText = "";
    document.getElementById("createProjectForm").removeEventListener("submit", updateProject);
    document.getElementById("createProjectForm").addEventListener("submit", createProject);
    document.getElementById("formTitle").innerText = "Add Project";
    document.getElementById("updateBack").style.display = "none";
}

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

function createProject(e){
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

const updateProject = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    let data = {};
    data["id"] = formData.get("id");
    data["title"] = formData.get("title");

    fetch(`${URL}/projects`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((result) => {
        result.json().then((project) => {
                closeUpdateProjectForm();
                fetchProjects();
        });
    }).catch((result) => {
        result.json().then((response) => {
            document.getElementById("errorUpdate").innerText = response.parameterViolations[0].message;
        });
    });
};

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
    let display = document.getElementById("display");
    display.innerHTML = "";
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

        const updateButton = document.createElement('button');
        updateButton.innerText = "Update";
        updateButton.onclick = function() {
            document.getElementById("id").value = project.id;
            document.getElementById("title").value = project.title;
            openUpdateProjectForm();
        }
        row.appendChild(updateButton);

        display.append(row);
    });
}

document.addEventListener('DOMContentLoaded', function(){
    closeUpdateProjectForm();
    fetchProjects();
});