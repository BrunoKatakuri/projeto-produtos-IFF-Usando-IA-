import { Link } from "react-router-dom";

export default function UserList({

    users,
    onDelete,
    onEdit

}) {

    return (

        <table className="table">

            <thead>

            <tr>

                <th>Nome</th>
                <th>Email</th>
                <th>Perfil</th>
                <th>Status</th>
                <th>Ações</th>

            </tr>

            </thead>

            <tbody>

            {users.map((user) => (

                <tr key={user.id}>

                    <td>{user.name}</td>

                    <td>{user.email}</td>

                    <td>{user.role}</td>

                    <td>

                        {user.active ? (

                            <span className="badge bg-success">
                                Ativo
                            </span>

                        ) : (

                            <span className="badge bg-danger">
                                Inativo
                            </span>

                        )}

                    </td>

                    <td className="d-flex gap-2">

                        <button
                            className="btn btn-warning btn-sm"
                            onClick={() =>
                                onEdit(user)
                            }
                        >
                            Editar
                        </button>

                        <Link
                            to={`/users/${user.id}`}
                            className="btn btn-info btn-sm"
                        >
                            Ver
                        </Link>

                        <button
                            className="btn btn-danger btn-sm"
                            onClick={() =>
                                onDelete(user.id)
                            }
                        >
                            Excluir
                        </button>

                    </td>

                </tr>

            ))}

            </tbody>

        </table>
    );
}