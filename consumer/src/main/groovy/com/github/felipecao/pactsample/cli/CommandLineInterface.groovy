package com.github.felipecao.pactsample.cli

import com.github.felipecao.pactsample.provider.StatusClient

class CommandLineInterface {

    private static final STATUS_COMMAND = "status"

    private static final QUIT_COMMAND = "quit"

    private StatusClient statusClient

    private InputStream inputStream

    CommandLineInterface(StatusClient statusClient, InputStream inputStream = null) {
        this.statusClient = statusClient
        this.inputStream = inputStream ?: System.in
    }

    void run() {
        inputStream.withReader {
            while (true) {
                String userCommand = readUserInput(it)

                if (userCommand.equalsIgnoreCase(QUIT_COMMAND)) {
                    System.exit(0)
                }

                if (!userCommand.equalsIgnoreCase(STATUS_COMMAND)) {
                    println("Command '${userCommand}' is not supported. Try '${STATUS_COMMAND}' or '${QUIT_COMMAND}' instead.")
                    continue
                }

                println(statusClient.retrieveProviderStatus())
            }
        }
    }

    private String readUserInput(Reader reader) {
        print "Enter command: "
        return reader.readLine().trim()
    }
}
