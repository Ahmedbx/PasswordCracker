import patoolib
import pyperclip

def extract_rar_with_password(rar_file_path, password):
    try:
        patoolib.extract_archive(rar_file_path, outdir='.', password=password)
        return True
    except patoolib.util.PatoolError:
        return False

def main():
    rar_file_path = r'C:\Users\ahmed\Desktop\Image.rar'

    generated_passwords = pyperclip.paste().split()

    if not generated_passwords:
        print("No passwords found in the clipboard.")
        return

    for password in generated_passwords:
        password = password.strip()
        print(f"Trying password: {password}")

        if extract_rar_with_password(rar_file_path, password):
            print(f"Correct password found: {password}")
            break
    else:
        print("Failed to find the correct password.")

if __name__ == "__main__":
    main()
