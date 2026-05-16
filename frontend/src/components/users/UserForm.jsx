import { useEffect, useState } from "react";

export default function UserForm({

    onSubmit,
    editingUser

}) {

    const [name, setName] =
        useState("");

    const [email, setEmail] =
        useState("");

    const [password, setPassword] =
        useState("");

    const [role, setRole] =
        useState("USER");

    const [active, setActive] =
        useState(true);

    useEffect(() => {

        if (editingUser) {

            setName(editingUser.name);
            setEmail(editingUser.email);
            setRole(editingUser.role);
            setActive(editingUser.active);
        }

    }, [editingUser]);

    const handleSubmit = (e) => {

        e.preventDefault();

        onSubmit({
            name,
            email,
            password,
            role,
            active
        });

        setName("");
        setEmail("");
        setPassword("");
        setRole("USER");
        setActive(true);
    };

    return (

        <form
            onSubmit={handleSubmit}
            className="card p-4 mb-4"
        >

            <h4>
                Usuário
            </h4>

            <input
                type="text"
                className="form-control mb-2"
                placeholder="Nome"
                value={name}
                onChange={(e) =>
                    setName(e.target.value)
                }
            />

            <input
                type="email"
                className="form-control mb-2"
                placeholder="Email"
                value={email}
                onChange={(e) =>
                    setEmail(e.target.value)
                }
            />

            <input
                type="password"
                className="form-control mb-2"
                placeholder="Senha"
                value={password}
                onChange={(e) =>
                    setPassword(e.target.value)
                }
            />

            <select
                className="form-select mb-2"
                value={role}
                onChange={(e) =>
                    setRole(e.target.value)
                }
            >

                <option value="USER">
                    USER
                </option>

                <option value="ADMIN">
                    ADMIN
                </option>

            </select>

            <div className="form-check mb-3">

                <input
                    type="checkbox"
                    className="form-check-input"
                    checked={active}
                    onChange={(e) =>
                        setActive(e.target.checked)
                    }
                />

                <label className="form-check-label">
                    Usuário ativo
                </label>

            </div>

            <button
                className="btn btn-primary"
                type="submit"
            >
                Salvar
            </button>

        </form>
    );
}