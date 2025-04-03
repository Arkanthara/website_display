import argparse
import os

parser = argparse.ArgumentParser()

parser.add_argument("-d", "--directory", required=True)

args = parser.parse_args()


def createGetter(line):
    words = line.split()

    return (
        "\n\tpublic "
        + words[1]
        + " get"
        + words[2].capitalize()
        + "() { \n\t\treturn this."
        + words[2]
        + ";\n\t}\n"
    )


def createField(line):
    words = line.split()
    return f"\tprivate {words[1]} {words[2]};\n"


if args.directory is not None:
    ls = os.listdir(args.directory)
    listfiles = [
        os.path.join(args.directory, file)
        for file in ls
        if os.path.isfile(os.path.join(args.directory, file))
    ]
    for file in listfiles:

        try:
            with open(file, "r") as f:
                lines = f.readlines()

                result = [
                    "package ems.models;\n\nimport jakarta.persistence.*;\nimport java.util.Date;\n\n@Entity\n"
                ]

                getter = []

                for line in lines:
                    if line.startswith("public class"):
                        words = line.split()
                        result.append(
                            f'@Table( name = "database_{words[2].lower()}" )\n'
                        )
                        result.append(line)
                    elif line.endswith("id ;\n"):
                        result.append(
                            "\t@Id\n\t@GeneratedValue(strategy = GenerationType.IDENTITY)\n"
                        )
                        result.append(createField(line))
                        getter.append(createGetter(line))
                    elif line.endswith("time ;\n"):
                        result.append("\n\t@Temporal(TemporalType.DATE)\n")
                        result.append(createField(line))
                        getter.append(createGetter(line))
                    elif line.endswith(";\n"):
                        result.append(createField(line))
                        getter.append(createGetter(line))
                    elif line.endswith("}\n"):
                        result += getter
                        result.append(line)
                for i in result:
                    print(i)
            with open(file, "w") as f:
                f.writelines(result)

        except Exception as e:
            print(f"An unexpected error occurs: {e}")
