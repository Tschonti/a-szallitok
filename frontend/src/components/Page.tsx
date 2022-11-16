import { Box, Flex } from "@chakra-ui/react";
import { Navbar } from "./Navbar";
import { HasChildren } from "../types/Utils";
import { Footer } from "./Footer";

export const Page = ({children}: HasChildren) => {
    return (
        <Flex direction="column" minHeight="100vh">
          <Navbar />
          <Box flex={1} pb={24} mx={["0.5rem", "3rem", "6rem", "10rem"]}>
            {children}
          </Box>
          <Footer />
        </Flex>
)}