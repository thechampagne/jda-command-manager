# JDA command manager

[![](https://img.shields.io/github/v/tag/thechampagne/jda-command-manager?label=version)](https://github.com/thechampagne/jda-command-manager/releases/latest) [![](https://img.shields.io/github/license/thechampagne/jda-command-manager)](https://github.com/thechampagne/jda-command-manager/blob/main/LICENSE)

JDA command manager.

### Download

Replace the **VERSION** key below with the version shown above.

**Gradle**
```gradle
dependencies {
    implementation 'io.github.thexxiv:jda-command-manager:VERSION'
}
```

**Maven**
```xml
<dependency>
    <groupId>io.github.thexxiv</groupId>
    <artifactId>jda-command-manager</artifactId>
    <version>VERSION</version>
</dependency>
```

### Example

```kotlin
fun main() {
    val manager = Manager("PREFIX")
        .addCommand(Hello())
    JDABuilder.createDefault("TOKEN")
        .addEventListeners(Listener(manager))
        .build().awaitReady()
}

class Hello : Command {
    override fun run(args: List<String>, event: GuildMessageReceivedEvent) {
        event.channel.sendMessage("Hi").queue()
    }

    override fun getCommand(): String {
        return "hello"
    }
}
```

### License

JDA command manager is released under the [MIT License](https://github.com/thechampagne/jda-command-manager/blob/main/LICENSE).