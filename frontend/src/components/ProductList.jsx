import api from '../services/api'
import { Link } from 'react-router-dom'

function ProductList({
    products,
    onProductDeleted,
    onEditProduct
}) {

    async function handleDelete(id) {

        const confirmed = window.confirm(
            'Deseja realmente excluir?'
        )

        if (!confirmed) {
            return
        }

        try {

            await api.delete(`/products/${id}`)

            onProductDeleted()

        } catch (error) {

            console.log(error)
        }
    }

    return (

        <div className="card shadow p-4">

            <h2 className="mb-4">
                Lista de Produtos
            </h2>

            <div className="table-responsive">

                <table className="table">

                <thead>

                <tr>

                    <th>Nome</th>
                    <th>Preço</th>
                    <th>Descrição</th>
                    <th>Ações</th>

                </tr>

                </thead>

                <tbody>

                {
                    products.map(product => (

                        <tr key={product.id}>

                            <td>{product.name}</td>

                            <td>
                                R$ {product.price}
                            </td>

                            <td>{product.description}</td>

                            <td className="d-flex gap-2">

                                <Link
                                    to={`/products/${product.id}`}
                                    className="btn btn-info btn-sm"
                                >
                                    Ver
                                </Link>

                                <button
                                    className="btn btn-warning btn-sm"
                                    onClick={() =>
                                        onEditProduct(product)
                                    }
                                >
                                    Editar
                                </button>

                                <button
                                    className="btn btn-danger btn-sm"
                                    onClick={() =>
                                        handleDelete(product.id)
                                    }
                                >
                                    Excluir
                                </button>

                            </td>

                        </tr>
                    ))
                }

                </tbody>

            </table>

        </div>

        </div>
    )
}

export default ProductList