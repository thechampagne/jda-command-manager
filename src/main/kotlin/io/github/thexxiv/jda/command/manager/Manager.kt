/*
 * Copyright (c) 2022 XXIV
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package io.github.thexxiv.jda.command.manager

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent

/**
 * JDA Command manager.
 *
 * @param PREFIX Something that you attach before every command.
 * @author XXIV
 */
class Manager(private var PREFIX: String) {
    private val commands: HashMap<String, Command> = HashMap()

    /**
     * Add command.
     *
     * @param command Class inherited from Command interface.
     * @see Command
     */
    fun addCommand(command: Command): Manager {
        if (!commands.containsKey(command.getCommand())) {
            commands[command.getCommand()] = command
        }
        return this
    }

    /**
     * Add commands.
     *
     * @param commandsArray Array of classes inherited from Command interface.
     * @see Command
     */
    fun addCommands(commandsArray: Array<Command>): Manager {
        for (command: Command in commandsArray) {
            if (!commands.containsKey(command.getCommand())) {
                commands[command.getCommand()] = command
            }
        }
        return this
    }

    internal fun run(event: GuildMessageReceivedEvent) {
        val message = event.message.contentRaw
        if (!message.startsWith(PREFIX)) {
            return
        }
        val split: Array<String> = message.replaceFirst(PREFIX, "").split(" ").toTypedArray()
        val command = split[0].lowercase()
        if (commands.containsKey(command)) {
            val args: List<String> = listOf(*split).subList(1, split.size)
            commands[command]?.run(args, event)
        }
    }
}