import os

# CONFIGURAÇÕES
mod_id = "good-old-times"
item_name = "meu_item_unico"  # <- passe o nome do item aqui

# Caminhos do seu projeto
base_path = "C:/Projetos/good-old-times-template-1.21.8/src/main/resources/assets"
lang_path = os.path.join(base_path, mod_id, "lang", "en_us.json")
models_item_path = os.path.join(base_path, mod_id, "models", "item")
textures_item_path = os.path.join(base_path, mod_id, "textures", "item")
items_path = os.path.join(base_path, mod_id, "items")

# Garante que as pastas existem
os.makedirs(models_item_path, exist_ok=True)
os.makedirs(textures_item_path, exist_ok=True)
os.makedirs(items_path, exist_ok=True)

# 1. Entrada no lang.json
lang_entry = f'  "item.{mod_id}.{item_name}": "{item_name.replace("_"," ").title()}",\n'

if os.path.exists(lang_path):
    with open(lang_path, "r+", encoding="utf-8") as f:
        conteudo = f.readlines()
        if lang_entry not in conteudo:
            conteudo.insert(-1, lang_entry)  # insere antes do fechamento
            f.seek(0)
            f.writelines(conteudo)
else:
    with open(lang_path, "w", encoding="utf-8") as f:
        f.write("{\n" + lang_entry + "}\n")

print(f"[✔] Entrada adicionada em {lang_path}")

# 2. Modelo do item
model_body = {
    "parent": "minecraft:item/generated",
    "textures": {
        "layer0": f"{mod_id}:item/{item_name}"
    }
}
model_file = os.path.join(models_item_path, f"{item_name}.json")
with open(model_file, "w", encoding="utf-8") as f:
    import json
    json.dump(model_body, f, indent=2, ensure_ascii=False)

print(f"[✔] Modelo criado em {model_file}")

# 3. Arquivo de definição do item (separado)
item_body = {
    "model": {
        "type": "minecraft:model",
        "model": f"{mod_id}:item/{item_name}"
    }
}

item_file = os.path.join(items_path, f"{item_name}.json")
with open(item_file, "w", encoding="utf-8") as f:
    import json
    json.dump(item_body, f, indent=2, ensure_ascii=False)

print(f"[✔] Arquivo do item criado em {item_file}")

# 4. Cria textura placeholder
texture_file = os.path.join(textures_item_path, f"{item_name}.png")
if not os.path.exists(texture_file):
    with open(texture_file, "wb") as f:
        pass  # cria um arquivo vazio (depois você substitui pelo PNG real)

print(f"[✔] Textura placeholder criada em {texture_file}")
