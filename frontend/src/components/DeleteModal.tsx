import { Modal, ModalOverlay, ModalContent, ModalHeader, ModalCloseButton, ModalBody, ModalFooter, Button } from "@chakra-ui/react"

type DeleteModalProps = {
    deleteFn: () => Promise<void>
    entityName: string
    onClose: () => void
    isOpen: boolean
}

export const DeleteModal = ({ deleteFn, entityName, onClose, isOpen }: DeleteModalProps) => {
    const onSubmit = () => {
        onClose()
        deleteFn()
    }

    return (
        <Modal isOpen={isOpen} onClose={onClose}>
            <ModalOverlay />
            <ModalContent>
            <ModalHeader>Are you sure</ModalHeader>
            <ModalCloseButton />
            <ModalBody>
                Are you sure you want to delete this {entityName}?
            </ModalBody>

            <ModalFooter>
                <Button variant='ghost' mr={3} onClick={onClose}>
                Cancel
                </Button>
                <Button variant='solid' onClick={onSubmit} colorScheme="red">Delete</Button>
            </ModalFooter>
            </ModalContent>
        </Modal>
    )
}