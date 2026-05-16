import { useEffect, useState } from "react";

import UserForm from "../../components/users/UserForm";
import UserList from "../../components/users/UserList";

import {

    getUsers,
    createUser,
    updateUser,
    deleteUser

} from "../../services/userService";

export default function UsersPage() {

    const [users, setUsers] =
        useState([]);

    const [editingUser, setEditingUser] =
        useState(null);

    const loadUsers = async () => {

        const data =
            await getUsers();

        setUsers(data);
    };

    useEffect(() => {

        loadUsers();

    }, []);

    const handleSubmit = async (user) => {

        if (editingUser) {

            await updateUser(
                editingUser.id,
                user
            );

            setEditingUser(null);

        } else {

            await createUser(user);
        }

        loadUsers();
    };

    const handleDelete = async (id) => {

        await deleteUser(id);

        loadUsers();
    };

    return (

        <div className="container mt-4">

            <h2 className="mb-4">
                Usuários
            </h2>

            <UserForm
                onSubmit={handleSubmit}
                editingUser={editingUser}
            />

            <UserList
                users={users}
                onDelete={handleDelete}
                onEdit={setEditingUser}
            />

        </div>
    );
}