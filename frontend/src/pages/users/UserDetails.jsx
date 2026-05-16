import { useEffect, useState } from "react";

import { useParams, Link } from "react-router-dom";

import {
    getUserById
} from "../../services/userService";

export default function UserDetails() {

    const { id } = useParams();

    const [user, setUser] =
        useState(null);

    const loadUser = async () => {

        const data =
            await getUserById(id);

        setUser(data);
    };

    useEffect(() => {

        loadUser();

    }, []);

    if (!user) {

        return <p>Carregando...</p>;
    }

    return (

        <div className="container mt-4">

            <div className="card p-4">

                <h2>
                    {user.name}
                </h2>

                <p>
                    <strong>Email:</strong>
                    {" "}
                    {user.email}
                </p>

                <p>
                    <strong>Perfil:</strong>
                    {" "}
                    {user.role}
                </p>

                <p>
                    <strong>Status:</strong>
                    {" "}
                    {user.active
                        ? "Ativo"
                        : "Inativo"}
                </p>

                <Link
                    to="/users"
                    className="btn btn-secondary"
                >
                    Voltar
                </Link>

            </div>

        </div>
    );
}