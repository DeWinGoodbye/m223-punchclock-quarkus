const URL = 'http://localhost:8080';
let users = [];
$(document).ready(function() {
    fetchUsers();
});


function openUpdateUserForm() {
    document.getElementById("error").innerText = "";
    document.getElementById("createUserForm").removeEventListener("submit", LoginUser);
    document.getElementById("createUserForm").addEventListener("submit", updateUser);
    document.getElementById("formTitle").innerText = "Update user";
    document.getElementById("updateBack").style.display = "block";
}

function closeUpdateUserForm() {
    document.getElementById("error").innerText = "";
    document.getElementById("createUserForm").removeEventListener("submit", updateUser);
    document.getElementById("createUserForm").addEventListener("submit", LoginUser);
    document.getElementById("formTitle").innerText = "Add User";
    document.getElementById("updateBack").style.display = "none";
}

function fetchUsers() {
    fetch(`${URL}/users`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            users = result;
        });
    });
}

function createUser(e){
    const data = {};
    const formData = new FormData(e.target);
    data["username"] = formData.get("username");
    data["password"] = formData.get("password");
    data["role"] = formData.get("role");
    e.preventDefault();
    fetch(`${URL}/users`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((result) => {
        result.json().then((user) => {
            fetchUsers();
        });
    });
}

const updateUser = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.target);
    let data = {};
    data["id"] = formData.get("id");
    data["username"] = formData.get("username");
    data["password"] = formData.get("password");
    data["role"] = formData.get("role");

    fetch(`${URL}/users`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((result) => {
        result.json().then((user) => {
                closeUpdateUserForm();
                fetchUsers();
        });
    }).catch((result) => {
        result.json().then((response) => {
            document.getElementById("errorUpdate").innerText = response.parameterViolations[0].message;
        });
    });
};

function deleteUser(id) {
    fetch(`${URL}/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(function() {
        fetchUsers();
    });
}

function LoginUser(e){
    const data= {};
    const formData = new FormData(e.target);
    data["username"] = formData.get("username");
    data["password"] = formData.get("password");

    e.preventDefault();
    fetch(`${URL}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then((result) => {
        result.text().then((text) => {
            localStorage.setItem("token", text);
            location.href = "index.html";
        })
    });
}


document.addEventListener('DOMContentLoaded', function(){
    closeUpdateUserForm();
    fetchUsers();
});