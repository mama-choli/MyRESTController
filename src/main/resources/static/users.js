function showUsersTable() {
    fetch("http://localhost:8080/admin/users").then(
        resp => {
            resp.json().then(
                usersData => {
                    console.log(usersData);
                    if (usersData.length > 0) {
                        let temp = "";
                        usersData.forEach(user => {
                            temp += `<tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.lastname}</td>
                                <td>${user.email}</td>
                                <td>${user.username}</td>
                                <td>${user.roles}</td>
                                <td><button class="btn btn-primary" id="editButton" data-toggle="modal" data-target="#editModal" onclick='editModal(${user.id})'>Edit</button></td>
                                <td><button class="btn btn-danger" id="deleteButton" data-toggle="modal" data-target="#deleteModal" onclick='deleteModal(${user.id})'>Delete</button></td>
                                </tr>`
                        })
                        document.getElementById("usersData").innerHTML = temp;
                    }
                }
            )
        })

    $('a[data-toggle="editModal"]').on('hidden', function() {
        $(this).data('editModal').$element.removeData()
    })
}