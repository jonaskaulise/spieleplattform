export default function Error({message} : ErrorProps) {
    return (
        <div className="max-width-center">
            <h1>{message}</h1>
        </div>
    )
}

interface ErrorProps {
    message: string
}

