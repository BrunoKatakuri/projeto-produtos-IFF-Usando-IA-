import { useEffect, useState } from 'react'
import api from '../services/api'
import { toast } from 'react-toastify'
import Spinner from 'react-bootstrap/Spinner'

function ProductForm({
    onProductCreated,
    editingProduct,
    clearEditing
}) {

    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)
    const [name, setName] = useState('')
    const [price, setPrice] = useState('')
    const [description, setDescription] = useState('')

    useEffect(() => {

        if (editingProduct) {

            setName(editingProduct.name)
            setPrice(editingProduct.price)
            setDescription(editingProduct.description)
        }

    }, [editingProduct])

    async function handleSubmit(event) {

        event.preventDefault()

        try {

            if (editingProduct) {

                await api.put(
                    `/products/${editingProduct.id}`,
                    {
                        name,
                        price,
                        description
                    },
                    toast.success('Produto salvo com sucesso')
                    
                )

                clearEditing()

            } else {

                await api.post(
                    '/products', {
                    name,
                    price,
                    description
                },
            toast.success('Produto salvo com sucesso'))
            }

            setName('')
            setPrice('')
            setDescription('')

            onProductCreated()

        } catch (error) {

            setError('Erro ao salvar produto')
            console.log(error)
            toast.error('Erro ao salvar produto')
        }
    }

    return (

        <div className="card shadow p-4 mb-4">

            <h2 className="mb-4">

                {
                    editingProduct
                        ? 'Editar Produto'
                        : 'Cadastrar Produto'
                }

            </h2>

            {
                error && (

                    <div className="alert alert-danger">

                        {error}

                    </div>
                )
            }   

            <form onSubmit={handleSubmit}>

                <div className="mb-3">

                    <label className="form-label">
                        Nome
                    </label>

                    <input
                        type="text"
                        className="form-control"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Preço
                    </label>

                    <input
                        type="number"
                        className="form-control"
                        value={price}
                        onChange={(e) => setPrice(e.target.value)}
                        required
                    />

                </div>

                <div className="mb-3">

                    <label className="form-label">
                        Descrição
                    </label>

                    <textarea
                        className="form-control"
                        value={description}
                        onChange={(e) => setDescription(e.target.value)}
                    />

                </div>

                <div className="d-flex gap-2">

                    <button
                        type="submit"
                        className="btn btn-primary"
                    >

                        {
                            editingProduct
                                ? 'Atualizar'
                                : loading
                                    ? (
                                        <Spinner
                                            animation="border"
                                            size="sm"
                                        />
                                    )
                                    : (
                                        editingProduct
                                            ? 'Atualizar'
                                            : 'Salvar'
                                    )
                        }

                    </button>

                    {
                        editingProduct && (

                            <button
                                type="button"
                                className="btn btn-secondary"
                                onClick={clearEditing}
                            >
                                Cancelar
                            </button>
                        )
                    }

                </div>

            </form>

        </div>
    )
}

export default ProductForm