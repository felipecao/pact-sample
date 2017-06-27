package com.github.felipecao.pactsample

import com.github.felipecao.pactsample.cli.CommandLineInterface
import com.github.felipecao.pactsample.provider.StatusClient

class Main {
    static void main(String[] args) {
        StatusClient statusClient = new StatusClient()
        InputStream inputStream = System.in
        CommandLineInterface cli = new CommandLineInterface(statusClient, inputStream)

        cli.run()
    }
}
