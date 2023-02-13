import "./Notification.scss"

interface NotificationProps {
    message: string
}
export default function Notification(props: NotificationProps) {
    return (
        <div className="notification">
            {props.message}
        </div>
    )
}
